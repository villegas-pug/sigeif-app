package microservice.sigesu.catalogo.service;

import java.util.List;

import microservice.sigesu.catalogo.dtos.CatalogoDto;

public interface CatalogoService {

   List<CatalogoDto> findAllCatalogosByGrupos(Integer grupo, Integer subgrupo);

}