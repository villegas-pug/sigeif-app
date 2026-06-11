package microservice.sigesu.reporting.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.sigesu.reporting.services.ReportService;
import microservice.shared_data.controller.BaseRestController;
import microservice.shared_data.exceptions.NotFoundException;

/**
 * Endpoints GET para los reportes transversales SIGES.
 * <p>
 * Convencion:
 * <ul>
 * <li>Un solo recurso expuesto en dos formatos:
 * <ul>
 * <li>{@code GET /reporte/{tipo}} -> JSON (dataset crudo del SP)</li>
 * <li>{@code GET /reporte/{tipo}/excel} -> XLSX (descarga)</li>
 * </ul>
 * </li>
 * <li>Paths en kebab-case; metodos Java en camelCase.</li>
 * <li>Nombre del archivo XLSX: {@code rpt-{tipo}-yyyy-MM-dd-HHmmss.xlsx}.</li>
 * <li>Dataset vacio => {@link NotFoundException} (politica transversal de
 * reporting).</li>
 * </ul>
 * </p>
 */
@RestController
@RequestMapping("/reporte")
@AllArgsConstructor
public class ReportController extends BaseRestController {

   private static final DateTimeFormatter FILE_TIMESTAMP = DateTimeFormatter.ofPattern("yyyy-MM-dd-HHmmss");
   private static final String EXCEL_EXTENSION = ".xlsx";

   private final ReportService service;

   // ============================================================
   // RPT_ASISTENCIA_ECONOMICA
   // Filtros: anioIngreso | dniUsuario | nombreUsuario | nombreCentro
   // ============================================================

   @GetMapping("/asistencia-economica")
   public ResponseEntity<List<Map<String, Object>>> getAsistenciaEconomicaReport(
         @RequestParam(name = "anioIngreso", required = false) String anioIngreso,
         @RequestParam(name = "dniUsuario", required = false) String dniUsuario,
         @RequestParam(name = "nombreUsuario", required = false) String nombreUsuario,
         @RequestParam(name = "nombreCentro", required = false) String nombreCentro) {

      List<Map<String, Object>> data = this.service.getAsistenciaEconomicaReportData(
            anioIngreso, dniUsuario, nombreUsuario, nombreCentro);

      if (data == null || data.isEmpty()) {
         throw new NotFoundException();
      }
      return ResponseEntity.ok(data);
   }

   @GetMapping("/asistencia-economica/excel")
   public ResponseEntity<byte[]> downloadAsistenciaEconomicaExcelReport(
         @RequestParam(name = "anioIngreso", required = false) String anioIngreso,
         @RequestParam(name = "dniUsuario", required = false) String dniUsuario,
         @RequestParam(name = "nombreUsuario", required = false) String nombreUsuario,
         @RequestParam(name = "nombreCentro", required = false) String nombreCentro) {

      byte[] excel = this.service.generateAsistenciaEconomicaExcelReport(
            anioIngreso, dniUsuario, nombreUsuario, nombreCentro);

      return super.buildDownloadResponseEntity(buildFilename("rpt-asistencia-economica"), excel);
   }

   // ============================================================
   // RPT_SIGEIR
   // Filtros: anioAfiliacion | dniUsuario | nombreUsuario | dniAdministrador |
   // nombreAdministrador | condicion
   // ============================================================

   @GetMapping("/sigeir")
   public ResponseEntity<List<Map<String, Object>>> getSigeirReport(
         @RequestParam(name = "anioAfiliacion", required = false) String anioAfiliacion,
         @RequestParam(name = "dniUsuario", required = false) String dniUsuario,
         @RequestParam(name = "nombreUsuario", required = false) String nombreUsuario,
         @RequestParam(name = "dniAdministrador", required = false) String dniAdministrador,
         @RequestParam(name = "nombreAdministrador", required = false) String nombreAdministrador,
         @RequestParam(name = "condicion", required = false) String condicion) {

      List<Map<String, Object>> data = this.service.getSigeirReportData(
            anioAfiliacion, dniUsuario, nombreUsuario,
            dniAdministrador, nombreAdministrador, condicion);

      if (data == null || data.isEmpty()) {
         throw new NotFoundException();
      }
      return ResponseEntity.ok(data);
   }

   @GetMapping("/sigeir/excel")
   public ResponseEntity<byte[]> downloadSigeirExcelReport(
         @RequestParam(name = "anioAfiliacion", required = false) String anioAfiliacion,
         @RequestParam(name = "dniUsuario", required = false) String dniUsuario,
         @RequestParam(name = "nombreUsuario", required = false) String nombreUsuario,
         @RequestParam(name = "dniAdministrador", required = false) String dniAdministrador,
         @RequestParam(name = "nombreAdministrador", required = false) String nombreAdministrador,
         @RequestParam(name = "condicion", required = false) String condicion) {

      byte[] excel = this.service.generateSigeirExcelReport(
            anioAfiliacion, dniUsuario, nombreUsuario,
            dniAdministrador, nombreAdministrador, condicion);

      return super.buildDownloadResponseEntity(buildFilename("rpt-sigeir"), excel);
   }

   // ============================================================
   // Helpers
   // ============================================================

   private String buildFilename(String baseName) {
      return baseName + "-" + LocalDateTime.now().format(FILE_TIMESTAMP) + EXCEL_EXTENSION;
   }

}
