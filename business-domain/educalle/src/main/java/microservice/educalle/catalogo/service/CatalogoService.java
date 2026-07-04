package microservice.educalle.catalogo.service;

import java.util.List;
import java.util.Map;

import microservice.educalle.catalogo.dtos.CatalogoDto;

public interface CatalogoService {

   List<CatalogoDto> findAllCatalogosByGrupos(Integer grupo, Integer subgrupo);

   List<Map<String, Object>> findAllNivelesEducativos(Integer idTipoEdu, Integer idNivelEdu);

}
