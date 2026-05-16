package microservice.sigesu.anexopregunta.repository;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import microservice.sigesu.anexopregunta.dtos.AnexoPregutasDto;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class AnexoPreguntasRepository extends BaseOracleRepository {

   public AnexoPreguntasRepository(JdbcTemplate jdbcTemplate, DataSource dataSource) {
      super(jdbcTemplate, dataSource);
   }

   public List<AnexoPregutasDto> findAllAnexoPregustasByParams(Integer idServicio, Integer anexo, Integer grupo) {

      Map<String, Object> inParams = new java.util.HashMap<>();
      inParams.put("p_servicio", idServicio);
      inParams.put("p_anexo", anexo);
      inParams.put("p_grupo", grupo);

      return this.executeProcedureWithInParams("USP_BUSCAR_PREGUNTAS_POR_PARAMETROS", inParams, "c_resultado",
            AnexoPregutasDto.class);
   }

   public List<AnexoPregutasDto> findAllAnexoPregustasByParams2(Integer idServicio, Integer anexo, Integer grupo) {

      Map<String, Object> inParams = new java.util.HashMap<>();
      inParams.put("p_servicio", idServicio);
      inParams.put("p_anexo", anexo);
      inParams.put("p_grupo", grupo);

      return this.executeProcedureWithInParams(
            "USP_BUSCAR_PREGUNTAS_POR_PARAMETROS2",
            inParams,
            "c_resultado",
            AnexoPregutasDto.class);
   }

}
