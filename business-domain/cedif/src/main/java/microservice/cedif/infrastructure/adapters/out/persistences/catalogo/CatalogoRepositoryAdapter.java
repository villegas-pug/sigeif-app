package microservice.cedif.infrastructure.adapters.out.persistences.catalogo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.cedif.domain.models.Catalogo;
import microservice.cedif.domain.ports.out.CatalogoRepositoryPort;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class CatalogoRepositoryAdapter extends BaseOracleRepository implements CatalogoRepositoryPort {

   public CatalogoRepositoryAdapter(JdbcTemplate jdbcTemplate, DataSource dataSource) {
      super(jdbcTemplate, dataSource);
   }

   @Override
   public List<Catalogo> findAllCatalogosByGrupos(Integer catGrupo, Integer catSubgrupo) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_catgrupo", catGrupo);
      inParams.put("p_catsubgrupo", catSubgrupo);
      return this.executeProcedureWithInParams("USP_LISTAR_CATALOGO_POR_GRUPOS", inParams,
            "c_resultado_busqueda", Catalogo.class);
   }

}
