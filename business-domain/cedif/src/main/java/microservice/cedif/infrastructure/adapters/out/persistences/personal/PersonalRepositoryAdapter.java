package microservice.cedif.infrastructure.adapters.out.persistences.personal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.cedif.domain.models.Personal;
import microservice.cedif.domain.ports.out.PersonalRepositoryPort;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class PersonalRepositoryAdapter extends BaseOracleRepository implements PersonalRepositoryPort {

   public PersonalRepositoryAdapter(JdbcTemplate jdbcTemplate, DataSource dataSource) {
      super(jdbcTemplate, dataSource);
   }

   @Override
   public List<Personal> findPersonalByDocumento(String nroDoc) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_numero_documento", nroDoc);
      return super.executeProcedureWithInParams("USP_LISTAR_PERSONAL_POR_DOCUMENTO", inParams, "c_resultado_busqueda",
            Personal.class);
   }

   @Override
   public List<Personal> findPersonalByParams(String nroDoc, String nombres) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_numero_documento", nroDoc);
      inParams.put("p_nombres", nombres);
      return super.executeProcedureWithInParams("USP_LISTAR_PERSONAL_POR_PARAMETROS", inParams, "c_resultado_busqueda",
            Personal.class);
   }

}
