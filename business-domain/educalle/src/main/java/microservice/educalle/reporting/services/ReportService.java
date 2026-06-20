package microservice.educalle.reporting.services;

import java.util.List;
import java.util.Map;

/**
 * Contrato del servicio de reportes transversales SIGES.
 * <p>
 * Cada metodo de datos expone el dataset crudo del SP; cada metodo de generacion
 * produce los bytes del XLSX correspondiente.
 * </p>
 */
public interface ReportService {

   // ============================================================
   // RPT_ASISTENCIA_ECONOMICA
   // ============================================================

   List<Map<String, Object>> getAsistenciaEconomicaReportData(
         String anioIngreso,
         String dniUsuario,
         String nombreUsuario,
         String nombreCentro);

   byte[] generateAsistenciaEconomicaExcelReport(
         String anioIngreso,
         String dniUsuario,
         String nombreUsuario,
         String nombreCentro);

   // ============================================================
   // RPT_SIGEIR
   // ============================================================

   List<Map<String, Object>> getSigeirReportData(
         String anioAfiliacion,
         String dniUsuario,
         String nombreUsuario,
         String dniAdministrador,
         String nombreAdministrador,
         String condicion);

   byte[] generateSigeirExcelReport(
         String anioAfiliacion,
         String dniUsuario,
         String nombreUsuario,
         String dniAdministrador,
         String nombreAdministrador,
         String condicion);

}
