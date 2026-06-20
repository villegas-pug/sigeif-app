package microservice.educalle.reporting.repository;

import java.util.List;
import java.util.Map;

/**
 * Repositorio para los reportes transversales SIGES (RPT_ASISTENCIA_ECONOMICA, RPT_SIGEIR).
 * Delega al SP {@code USP_GENERAR_REPORTES_SIGES} a traves de {@link microservice.shared_data.repositories.BaseOracleRepository}.
 */
public interface ReportRepository {

   List<Map<String, Object>> executeAsistenciaEconomicaReport(String pValor);

   List<Map<String, Object>> executeSigeirReport(String pValor);

}
