package microservice.educalle.catalogo.repository;

import java.util.List;

import microservice.educalle.catalogo.dtos.CatalogoDto;

public interface CatalogoRepository {
   public List<CatalogoDto> findAllCatalogosByGrupos(Integer catGrupo, Integer catSubgrupo);
}
