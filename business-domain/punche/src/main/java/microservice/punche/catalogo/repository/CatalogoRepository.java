package microservice.punche.catalogo.repository;

import java.util.List;

import microservice.punche.catalogo.dtos.CatalogoDto;

public interface CatalogoRepository {
   public List<CatalogoDto> findAllCatalogosByGrupos(Integer catGrupo, Integer catSubgrupo);
}
