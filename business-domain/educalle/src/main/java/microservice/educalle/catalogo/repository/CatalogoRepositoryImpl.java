package microservice.educalle.catalogo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import microservice.educalle.catalogo.dtos.CatalogoDto;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class CatalogoRepositoryImpl extends BaseOracleRepository implements CatalogoRepository {

   public CatalogoRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
      super(jdbcTemplate, dataSource);
   }

   public List<CatalogoDto> findAllCatalogosByGrupos(Integer catGrupo, Integer catSubgrupo) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_catgrupo", catGrupo);
      inParams.put("p_catsubgrupo", catSubgrupo);
      return this.executeProcedureWithInParams("USP_LISTAR_CATALOGO_POR_GRUPOS", inParams,
            "c_resultado_busqueda", CatalogoDto.class);
   }

   @Override
   public List<Map<String, Object>> findAllNivelesEducativos(Integer idTipoEdu, Integer idNivelEdu) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_tipo_edu", idTipoEdu);
      inParams.put("p_id_nivel_edu", idNivelEdu);
      return this.executeProcedureAndFetchResult("PRC_SISEC_CATALOGO_LISTAR_NIVEL_EDU", inParams, "p_cursor");
   }

}
