package microservice.educalle.reporting.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.shared_data.repositories.BaseOracleRepository;

/**
 * Implementacion del repositorio de reportes.
 * Invoca el SP {@code USP_GENERAR_REPORTES_SIGES} segun el tipo y devuelve un dataset
 * de tipo {@code List<Map<String, Object>>} para su consumo tanto en JSON como en Excel dinamico.
 */
@Repository
public class ReportRepositoryImpl extends BaseOracleRepository implements ReportRepository {

   private static final String SP_NAME = "USP_GENERAR_REPORTES_SIGES";
   private static final String PARAM_TIPO = "p_tipo";
   private static final String PARAM_VALOR = "p_valor";
   private static final String PARAM_RESULTADO = "p_resultado";

   private static final String TIPO_ASISTENCIA_ECONOMICA = "RPT_ASISTENCIA_ECONOMICA";
   private static final String TIPO_SIGEIR = "RPT_SIGEIR";

   public ReportRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
      super(jdbcTemplate, dataSource);
   }

   @Override
   public List<Map<String, Object>> executeAsistenciaEconomicaReport(String pValor) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put(PARAM_TIPO, TIPO_ASISTENCIA_ECONOMICA);
      inParams.put(PARAM_VALOR, pValor);
      return super.executeProcedureAndFetchResult(SP_NAME, inParams, PARAM_RESULTADO);
   }

   @Override
   public List<Map<String, Object>> executeSigeirReport(String pValor) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put(PARAM_TIPO, TIPO_SIGEIR);
      inParams.put(PARAM_VALOR, pValor);
      return super.executeProcedureAndFetchResult(SP_NAME, inParams, PARAM_RESULTADO);
   }

}
