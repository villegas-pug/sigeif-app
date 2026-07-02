package microservice.scheduling.siges.infrastructure;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.scheduling.siges.domain.GenerateMatrizFichasSigeUseCase;
import microservice.scheduling.siges.exceptions.MatrizFichasSigeNotFoundException;
import microservice.scheduling.siges.properties.MatrizFichasSigeProperties;
import microservice.scheduling.shared.repository.BaseRepository;
import microservice.scheduling.shared.service.BaseReportingService;

@Log4j2
@Service
@AllArgsConstructor
public class GenerateMatrizFichasSigeUseCaseImpl
      extends BaseReportingService
      implements GenerateMatrizFichasSigeUseCase {

   // * Columnas de dimensión que se conservan como columnas en cada hoja
   // * (en el orden en que aparecerán a la izquierda de la grilla).
   // * NO incluye ANEXO ni NUMERO_PREGUNTA.
   private static final List<String> DIMENSION_COLUMNS = List.of(
         "ID", "PERIODO", "TIPO", "CODIGO", "INSTRUMENTO", "CORRELATIVO",
         "UNIDAD", "SERVICIO", "PERFIL", "CENTRO", "DEPARTAMENTO_PROVINCIA_DISTRITO",
         "RESPONSABLE_SUPERVISION", "DIRECTOR_COORDINADOR", "FECHA_DE_REGISTRO");

   // * Columnas del cursor que participan en el pivot.
   private static final String COL_NUMERO_PREGUNTA = "NUMERO_PREGUNTA";
   private static final String COL_NUMERO_GRUPO = "NUMERO_GRUPO";
   private static final String COL_ANEXO = "ANEXO";
   private static final String COL_CODIGO = "CODIGO";

   // * Pares (pregunta, respuesta) que se pivotan a columnas.
   // * El orden de aparición en la lista define el pairIndex (0 = primera, 1 =
   // segunda, etc.).
   // * skipEmpty=true omite la fila cuando la pregunta viene null/vacía en esa
   // fila.
   // * Por regla: solo PREGUNTAS2 omite vacíos (PREGUNTAS2 es sub-pregunta de
   // PREGUNTAS).
   private record PivotPair(String preguntaCol, String respuestaCol, boolean skipEmpty) {
   }

   private static final List<PivotPair> PIVOT_PAIRS = List.of(
         new PivotPair("PREGUNTAS", "RESPUESTAS", false),
         new PivotPair("PREGUNTAS2", "RESPUESTAS2", true));

   // * SP fijo de este reporte.
   private static final String SP_NAME = "USP_GENERAR_REPORTES_SIGES";
   private static final String OUT_CURSOR = "p_resultado";

   // * Triplete usado para ordenar las columnas pivoteadas.
   // * pairIndex actúa como tie-breaker: cuando dos preguntas empatan en (g, n),
   // * gana la del par con índice menor (PREGUNTAS antes que PREGUNTAS2,
   // * porque PREGUNTAS2 es sub-pregunta de PREGUNTAS).
   private record PreguntaOrden(int grupo, int pregunta, int pairIndex) {
   }

   private final MatrizFichasSigeProperties properties;
   private final BaseRepository repository;

   @Override
   public void generateMatrizFichasSigeReport() throws IOException {

      // * 1. Ejecutar SP con IN params hardcoded (RPT_TODO, NULL)
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_tipo", "RPT_TODO");
      inParams.put("p_valor", null);

      List<Map<String, Object>> dataset = this.repository.executeProcedureAndFetchResult(
            SP_NAME, inParams, OUT_CURSOR);

      // * 2. Gate transversal SIGEIF: dataset vacío → NotFoundException
      if (dataset.isEmpty()) {
         throw new MatrizFichasSigeNotFoundException();
      }

      // * 3. Agrupar filas por (ANEXO + CODIGO) → una hoja por grupo, formato:
      // ANEXO(CODIGO)
      Map<String, List<Map<String, Object>>> groups = new LinkedHashMap<>();
      for (Map<String, Object> row : dataset) {
         String codigo = str(row.get(COL_CODIGO));
         String anexo = str(row.get(COL_ANEXO));
         String sheetKey = anexo + "(" + codigo + ")";
         groups.computeIfAbsent(sheetKey, k -> new ArrayList<>()).add(row);
      }

      // * 4. Crear directorio destino si no existe
      Path outputDir = Paths.get(this.getBaseOutputPath());
      if (!Files.exists(outputDir)) {
         Files.createDirectories(outputDir);
      }
      Path fullPath = outputDir.resolve(this.getFileName() + ".xlsx");

      // * 5. Construir workbook multi-hoja, ordenando las hojas por ANEXO ASC
      try (XSSFWorkbook workbook = new XSSFWorkbook();
            FileOutputStream fos = new FileOutputStream(fullPath.toFile())) {

         List<Map.Entry<String, List<Map<String, Object>>>> orderedGroups = groups.entrySet().stream()
               .sorted(Comparator.comparing(e -> {
                  String key = e.getKey();
                  int idx = key.indexOf('(');
                  return idx > 0 ? key.substring(0, idx) : key;
               }))
               .toList();

         for (Map.Entry<String, List<Map<String, Object>>> entry : orderedGroups) {
            String sheetName = sanitizeSheetName(entry.getKey());
            writePivotSheet(workbook, sheetName, entry.getValue());
         }

         workbook.write(fos);
      }

      log.info("Reporte generado: {}", fullPath);
   }

   // ------------------------------------------------------------------
   // * Pivot + escritura de una hoja
   // ------------------------------------------------------------------
   private void writePivotSheet(XSSFWorkbook workbook, String sheetName,
         List<Map<String, Object>> rows) {

      XSSFSheet sheet = workbook.createSheet(sheetName);

      // * 1. Preguntas únicas ordenadas por (NUMERO_GRUPO, NUMERO_PREGUNTA,
      // pairIndex).
      // * pairIndex es el tie-breaker: cuando hay empate en (g, n), gana la pregunta
      // * del par con índice menor (PREGUNTAS antes que PREGUNTAS2).
      // * Estable: primer orden de aparición como desempate final (LinkedHashMap).
      Map<String, PreguntaOrden> preguntaOrder = new LinkedHashMap<>();
      int pairIndex = 0;
      for (PivotPair pair : PIVOT_PAIRS) {
         for (Map<String, Object> r : rows) {
            String pregunta = str(r.get(pair.preguntaCol()));
            if (pair.skipEmpty() && pregunta.isBlank())
               continue;
            preguntaOrder.putIfAbsent(pregunta,
                  new PreguntaOrden(
                        safeInt(r.get(COL_NUMERO_GRUPO)),
                        safeInt(r.get(COL_NUMERO_PREGUNTA)),
                        pairIndex));
         }
         pairIndex++;
      }
      List<String> preguntaCols = preguntaOrder.entrySet().stream()
            .sorted(Comparator
                  .comparingInt((Map.Entry<String, PreguntaOrden> e) -> e.getValue().grupo)
                  .thenComparingInt(e -> e.getValue().pregunta)
                  .thenComparingInt(e -> e.getValue().pairIndex))
            .map(Map.Entry::getKey)
            .toList();

      // * 2. Fichas únicas (tupla de dimensiones) preservando primer orden de
      // aparición
      Map<String, Map<String, Object>> fichaIndex = new LinkedHashMap<>();
      for (Map<String, Object> r : rows) {
         String fichaKey = dimensionKey(r);
         fichaIndex.putIfAbsent(fichaKey, r);
      }
      List<Map<String, Object>> fichas = new ArrayList<>(fichaIndex.values());

      // * 3. Lookup rápido (fichaKey + pregunta) -> respuesta (siempre en mayúscula)
      // * Clave compuesta: "fichaKey\u0001pregunta" para evitar colisiones por
      // concatenación.
      // * Itera sobre PIVOT_PAIRS respetando el flag skipEmpty (PREGUNTAS2 omite
      // vacíos).
      Map<String, String> cellLookup = new HashMap<>();
      for (Map<String, Object> r : rows) {
         String fichaKey = dimensionKey(r);
         for (PivotPair pair : PIVOT_PAIRS) {
            String pregunta = str(r.get(pair.preguntaCol()));
            if (pair.skipEmpty() && pregunta.isBlank())
               continue;
            String respuesta = str(r.get(pair.respuestaCol())).toUpperCase(Locale.ROOT);
            cellLookup.put(fichaKey + "" + pregunta, respuesta);
         }
      }

      // * LOG TEMPORAL: diagnóstico de render de respuestas (remover tras verificar el fix).
      log.info("[MATRIZ_SIGES] sheet='{}' rows={} fichas={} preguntaCols={} cellLookup={}",
            sheetName, rows.size(), fichas.size(), preguntaCols.size(), cellLookup.size());
      if (!cellLookup.isEmpty()) {
         log.info("[MATRIZ_SIGES] preguntaCols: {}", preguntaCols);
         log.info("[MATRIZ_SIGES] cellLookup sample (max 3):");
         cellLookup.entrySet().stream().limit(3).forEach(e ->
               log.info("[MATRIZ_SIGES]   key='{}' value='{}'", e.getKey(), e.getValue()));
      }

      // * 4. Cabeceras: dimensiones + preguntas pivoteadas
      List<String> headers = new ArrayList<>(DIMENSION_COLUMNS);
      headers.addAll(preguntaCols);

      XSSFRow headerRow = sheet.createRow(0);
      for (int i = 0; i < headers.size(); i++) {
         headerRow.createCell(i).setCellValue(headers.get(i).toUpperCase());
      }

      // * 5. Cuerpo: una fila por ficha, celdas = respuesta
      int rowIdx = 1;
      for (Map<String, Object> ficha : fichas) {
         String fichaKey = dimensionKey(ficha);
         XSSFRow row = sheet.createRow(rowIdx++);

         for (int i = 0; i < DIMENSION_COLUMNS.size(); i++) {
            row.createCell(i).setCellValue(str(ficha.get(DIMENSION_COLUMNS.get(i))));
         }
         for (int i = 0; i < preguntaCols.size(); i++) {
            String val = cellLookup.get(fichaKey + "\u0001" + preguntaCols.get(i));
            row.createCell(DIMENSION_COLUMNS.size() + i).setCellValue(val == null ? "" : val);
         }
      }

      // * 6. Estilos básicos (header bold, freeze fila 0, ancho columna)
      applyBasicHeaderCellStyle(workbook, sheet);
      applyBasicBodyCellStyle(workbook, sheet);
   }

   // ------------------------------------------------------------------
   // * Estilos (réplica local de BaseApachePOIReportingService, aplicada por hoja)
   // ------------------------------------------------------------------
   private void applyBasicHeaderCellStyle(XSSFWorkbook workbook, Sheet sheet) {
      if (sheet.getRow(0) == null)
         return;

      CellStyle cellStyle = workbook.createCellStyle();
      Font fuente = workbook.createFont();
      fuente.setColor(IndexedColors.BLACK.getIndex());
      fuente.setBold(true);
      fuente.setFontHeightInPoints((short) 10);
      fuente.setFontName("Arial");
      cellStyle.setFont(fuente);

      cellStyle.setBorderLeft(BorderStyle.THIN);
      cellStyle.setBorderRight(BorderStyle.THIN);
      cellStyle.setBorderTop(BorderStyle.THIN);
      cellStyle.setBorderBottom(BorderStyle.THIN);
      cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      cellStyle.setAlignment(HorizontalAlignment.CENTER);
      cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

      cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
      cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

      sheet.getRow(0).setHeightInPoints(25);
      sheet.createFreezePane(0, 1);

      for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
         sheet.getRow(0).getCell(i).setCellStyle(cellStyle);
      }
   }

   private void applyBasicBodyCellStyle(XSSFWorkbook workbook, Sheet sheet) {
      if (sheet.getRow(0) == null)
         return;

      CellStyle cellStyle = workbook.createCellStyle();
      cellStyle.setBorderLeft(BorderStyle.THIN);
      cellStyle.setBorderRight(BorderStyle.THIN);
      cellStyle.setBorderTop(BorderStyle.THIN);
      cellStyle.setBorderBottom(BorderStyle.THIN);
      cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
      cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
      cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
      cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
      cellStyle.setAlignment(HorizontalAlignment.CENTER);
      cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

      Font font = workbook.createFont();
      font.setColor(IndexedColors.BLACK.getIndex());
      font.setFontHeightInPoints((short) 9);
      font.setFontName("Arial");
      cellStyle.setFont(font);

      for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
         int headerLength = sheet.getRow(0).getCell(i).getStringCellValue().length();
         // * Excel limita el ancho de columna a 255 caracteres (65 280 unidades).
         // * Si el header (o el contenido típico de la columna) excede eso, cap al
         // máximo permitido.
         int cellWidth = Math.min(255, headerLength + 5) * 256;
         sheet.setColumnWidth(i, cellWidth);
      }

      for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
         if (sheet.getRow(i) == null)
            continue;
         for (int j = 0; j < sheet.getRow(i).getLastCellNum(); j++) {
            sheet.getRow(i).getCell(j).setCellStyle(cellStyle);
         }
      }
   }

   // ------------------------------------------------------------------
   // * Util
   // ------------------------------------------------------------------
   private static String dimensionKey(Map<String, Object> row) {
      return DIMENSION_COLUMNS.stream()
            .map(c -> str(row.get(c)))
            .collect(Collectors.joining("\u0001"));
   }

   private static String str(Object o) {
      return o == null ? "" : o.toString();
   }

   private static int safeInt(Object o) {
      if (o == null)
         return Integer.MAX_VALUE;
      try {
         return Integer.parseInt(o.toString().trim());
      } catch (NumberFormatException e) {
         return Integer.MAX_VALUE;
      }
   }

   private static String sanitizeSheetName(String raw) {
      String s = raw == null ? "" : raw;
      // * Caracteres prohibidos por Excel en nombres de hoja
      s = s.replaceAll("[\\\\/:*?\\[\\]]", "_");
      // * Límite Excel: 31 chars
      if (s.length() > 31) {
         s = s.substring(0, 31);
      }
      return s.isBlank() ? "Sheet" : s;
   }

   // ------------------------------------------------------------------
   // * Implementación de abstractos de BaseReportingService
   // ------------------------------------------------------------------
   @Override
   protected String getBaseOutputPath() {
      return this.properties.getExcel().getOutputPath();
   }

   @Override
   protected String getFileName() {
      return this.properties.getExcel().getFileName();
   }

}
