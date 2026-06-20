package microservice.educalle.personal.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.educalle.personal.dtos.PersonalDto;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class PersonalRepositoryImpl extends BaseOracleRepository implements PersonalRepository {

   private final PersonalJpaRepository repository;

   PersonalRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource, PersonalJpaRepository repository) {
      super(jdbcTemplate, dataSource);
      this.repository = repository;
   }

   @Override
   public List<PersonalDto> findPersonalByDocumento(String nroDoc) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_numero_documento", nroDoc);
      return super.executeProcedureWithInParams("USP_LISTAR_PERSONAL_POR_DOCUMENTO", inParams,
            "c_resultado_busqueda", PersonalDto.class);
   }

   @Override
   public Optional<PersonalEntity> findPersonalById(Integer idPersonal) {
      return this.repository.findById((long) idPersonal);
   }

   @Override
   public List<PersonalDto> findPersonalByDynamicParam(Integer tipoBusqueda, String dynamicValue) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_tipo_busqueda", tipoBusqueda);
      inParams.put("p_dynamic_param", dynamicValue);
      return super.executeProcedureWithInParams("USP_LISTAR_PERSONAL_POR_DYNAMIC_PARAM", inParams,
            "c_resultado_busqueda", PersonalDto.class);
   }

}
