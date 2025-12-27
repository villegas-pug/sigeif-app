package microservice.punche.catalogo.service;

import java.util.List;

import microservice.punche.catalogo.dtos.CatalogoDto;

public interface CatalogoService {

   List<CatalogoDto> findAllCatalogosByGrupos(Integer grupo, Integer subgrupo);

}