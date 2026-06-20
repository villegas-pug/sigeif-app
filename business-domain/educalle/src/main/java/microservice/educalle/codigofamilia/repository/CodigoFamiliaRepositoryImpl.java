package microservice.educalle.codigofamilia.repository;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import microservice.shared_data.enums.InabifServices;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class CodigoFamiliaRepositoryImpl extends BaseOracleRepository implements CodigoFamiliaRepository {

   public CodigoFamiliaRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
      super(jdbcTemplate, dataSource);
   }

   @Override
   public String generateCodFamilia(Long idFamilia) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_servicio", InabifServices.EDUCALLE.getId());
      inParams.put("p_id_familia", idFamilia);
      inParams.put("p_id_integrante", null);
      return this.executeProcedureWithOutParam("USP_GENERAR_CODIGO_FAMILIA", inParams, "o_codigo_familia",
            String.class);
   }

   @Override
   public String generateCodIntegrante(Long idIntegrante) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_servicio", InabifServices.EDUCALLE.getId());
      inParams.put("p_id_familia", null);
      inParams.put("p_id_integrante", idIntegrante);
      return this.executeProcedureWithOutParam("USP_GENERAR_CODIGO_FAMILIA", inParams, "o_codigo_familia",
            String.class);
   }

}
