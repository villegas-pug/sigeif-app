package microservice.punche.unidadfuncionalusuario.service;

import java.util.List;
import microservice.punche.unidadfuncionalusuario.dtos.UnidadFuncionalUsuarioQuery;
import microservice.punche.unidadfuncionalusuario.model.UnidadFuncionalUsuario;

public interface UnidadFuncionalUsuarioService {

   List<UnidadFuncionalUsuario> findUnidadFuncionalUsuarioByIdUsuario(UnidadFuncionalUsuarioQuery query);
}
