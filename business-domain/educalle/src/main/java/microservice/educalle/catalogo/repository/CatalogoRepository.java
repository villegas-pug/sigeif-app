package microservice.educalle.catalogo.repository;

import java.util.List;
import java.util.Map;

import microservice.educalle.catalogo.dtos.CatalogoDto;

public interface CatalogoRepository {
   public List<CatalogoDto> findAllCatalogosByGrupos(Integer catGrupo, Integer catSubgrupo);

   List<Map<String, Object>> findAllNivelesEducativos(Integer idTipoEdu, Integer idNivelEdu);
}
