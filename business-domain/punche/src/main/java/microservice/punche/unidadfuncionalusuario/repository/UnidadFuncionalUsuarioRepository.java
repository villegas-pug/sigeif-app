package microservice.punche.unidadfuncionalusuario.repository;

import java.util.List;
import java.util.Map;

public interface UnidadFuncionalUsuarioRepository {

   List<Map<String, Object>> findUnidadFuncionalUsuarioByIdUsuario(Long idUsuario);
}
