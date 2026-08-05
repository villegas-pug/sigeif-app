package microservice.punche.unidadfuncionalusuario.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class UnidadFuncionalUsuarioRepositoryImpl extends BaseOracleRepository
      implements UnidadFuncionalUsuarioRepository {

   public UnidadFuncionalUsuarioRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
      super(jdbcTemplate, dataSource);
   }

   @Override
   public List<Map<String, Object>> findUnidadFuncionalUsuarioByIdUsuario(Long idUsuario) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_usuario", idUsuario);

      return this.executeProcedureAndFetchResult(
            "PRC_UNIDAD_FUNCIONAL_POR_USUARIO", inParams, "p_cursor_out");
   }
}
