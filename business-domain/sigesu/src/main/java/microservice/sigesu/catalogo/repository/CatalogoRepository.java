package microservice.sigesu.catalogo.repository;

import java.util.List;

import microservice.sigesu.catalogo.dtos.CatalogoDto;

public interface CatalogoRepository {
   public List<CatalogoDto> findAllCatalogosByGrupos(Integer catGrupo, Integer catSubgrupo);
}
