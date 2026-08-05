package microservice.punche.unidadfuncionalusuario.service;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.unidadfuncionalusuario.dtos.UnidadFuncionalUsuarioQuery;
import microservice.punche.unidadfuncionalusuario.model.UnidadFuncionalUsuario;
import microservice.punche.unidadfuncionalusuario.repository.UnidadFuncionalUsuarioRepository;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class UnidadFuncionalUsuarioServiceImpl implements UnidadFuncionalUsuarioService {

   private final UnidadFuncionalUsuarioRepository repository;

   @Override
   @Transactional(readOnly = true)
   public List<UnidadFuncionalUsuario> findUnidadFuncionalUsuarioByIdUsuario(UnidadFuncionalUsuarioQuery query) {
      List<Map<String, Object>> rows = this.repository.findUnidadFuncionalUsuarioByIdUsuario(query.getIdUsuario());
      List<UnidadFuncionalUsuario> unidadesFuncionales = rows.stream()
            .map(this::toModel)
            .toList();
      if (unidadesFuncionales.isEmpty()) {
         throw new NotFoundException();
      }
      return unidadesFuncionales;
   }

   private UnidadFuncionalUsuario toModel(Map<String, Object> row) {
      return UnidadFuncionalUsuario.builder()
            .idUsuario(toLong(row.get("IDUSUARIO")))
            .centroNombre((String) row.get("CENTRO_NOMBRE"))
            .idUnidadOrganica(toLong(row.get("IDUNIDADORGANICA")))
            .uorNombre((String) row.get("UORNOMBRE"))
            .uorAbreviatura((String) row.get("UORABREVIATURA"))
            .uorServicioPadre(toLong(row.get("UOR_SERVICIO_PADRE")))
            .zoIdZona(toLong(row.get("ZO_ID_ZONA")))
            .zoDescripcion((String) row.get("ZO_DESCRIPCION"))
            .build();
   }

   private Long toLong(Object value) {
      return value == null ? null : ((Number) value).longValue();
   }
}
