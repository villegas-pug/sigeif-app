package microservice.cedif.infrastructure.adapters.out.persistences.anexopregunta;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.cedif.domain.models.AnexoPregunta;
import microservice.cedif.domain.ports.out.AnexoPregutaRepositoryPort;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class AnexoPreguntaRepositoryAdapter extends BaseOracleRepository implements AnexoPregutaRepositoryPort {

   public AnexoPreguntaRepositoryAdapter(JdbcTemplate jdbcTemplate, DataSource dataSource) {
      super(jdbcTemplate, dataSource);
   }

   @Override
   public List<AnexoPregunta> findAllAnexoPregustasByParams(Integer idServicio, Integer anexo, Integer grupo) {

      Map<String, Object> inParams = new java.util.HashMap<>();
      inParams.put("p_servicio", idServicio);
      inParams.put("p_anexo", anexo);
      inParams.put("p_grupo", grupo);

      return this.executeProcedureWithInParams("USP_BUSCAR_PREGUNTAS_POR_PARAMETROS", inParams, "c_resultado",
            AnexoPregunta.class);
   }

}
