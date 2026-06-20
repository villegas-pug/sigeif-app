package microservice.educalle.reporting.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.educalle.reporting.repository.ReportRepository;
import microservice.shared_data.exceptions.NotFoundException;
import microservice.shared_data.services.BaseReportingService;

/**
 * Implementacion del servicio de reportes transversales SIGES.
 * <p>
 * - Recibe los filtros como parametros individuales (mas amigable para el consumidor)
 *   y los concatena con {@code |} usando {@code SF} como sentinela de "no aplica",
 *   tal como espera el SP {@code USP_GENERAR_REPORTES_SIGES}.
 * - El dataset siempre es {@code List<Map<String, Object>>}. Si esta vacio,
 *   se lanza {@link NotFoundException} (politica transversal de reporting).
 * - La generacion de Excel se delega a {@code super.apachePOIReportingService.generateDynamicExcelFile}.
 * </p>
 */
@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ReportServiceImpl extends BaseReportingService implements ReportService {

   private static final String FILTER_EMPTY_VALUE = "SF";
   private static final String FILTER_SEPARATOR = "|";
   private static final String EXCEL_SHEET_NAME = "░";

   private final ReportRepository repository;

   // ============================================================
   // RPT_ASISTENCIA_ECONOMICA
   // ============================================================

   @Override
   public List<Map<String, Object>> getAsistenciaEconomicaReportData(
         String anioIngreso,
         String dniUsuario,
         String nombreUsuario,
         String nombreCentro) {

      String pValor = joinFilters(anioIngreso, dniUsuario, nombreUsuario, nombreCentro);
      return this.repository.executeAsistenciaEconomicaReport(pValor);
   }

   @Override
   public byte[] generateAsistenciaEconomicaExcelReport(
         String anioIngreso,
         String dniUsuario,
         String nombreUsuario,
         String nombreCentro) {

      List<Map<String, Object>> dataset = getAsistenciaEconomicaReportData(
            anioIngreso, dniUsuario, nombreUsuario, nombreCentro);

      validateDataset(dataset);
      return super.apachePOIReportingService.generateDynamicExcelFile(EXCEL_SHEET_NAME, dataset);
   }

   // ============================================================
   // RPT_SIGEIR
   // ============================================================

   @Override
   public List<Map<String, Object>> getSigeirReportData(
         String anioAfiliacion,
         String dniUsuario,
         String nombreUsuario,
         String dniAdministrador,
         String nombreAdministrador,
         String condicion) {

      String pValor = joinFilters(
            anioAfiliacion,
            dniUsuario,
            nombreUsuario,
            dniAdministrador,
            nombreAdministrador,
            condicion);

      return this.repository.executeSigeirReport(pValor);
   }

   @Override
   public byte[] generateSigeirExcelReport(
         String anioAfiliacion,
         String dniUsuario,
         String nombreUsuario,
         String dniAdministrador,
         String nombreAdministrador,
         String condicion) {

      List<Map<String, Object>> dataset = getSigeirReportData(
            anioAfiliacion, dniUsuario, nombreUsuario,
            dniAdministrador, nombreAdministrador, condicion);

      validateDataset(dataset);
      return super.apachePOIReportingService.generateDynamicExcelFile(EXCEL_SHEET_NAME, dataset);
   }

   // ============================================================
   // Helpers
   // ============================================================

   /**
    * Concatena los filtros con {@code |}, sustituyendo nulos/vacios por {@code SF}.
    * Preserva el orden posicional requerido por el SP.
    */
   private String joinFilters(String... filters) {
      return Stream.of(filters)
            .map(f -> (f == null || f.isBlank()) ? FILTER_EMPTY_VALUE : f.trim())
            .collect(Collectors.joining(FILTER_SEPARATOR));
   }

   private void validateDataset(List<Map<String, Object>> dataset) {
      if (dataset == null || dataset.isEmpty()) {
         throw new NotFoundException();
      }
   }

   @Override
   protected String getBaseTemplatePath() {
      return "";
   }

}
