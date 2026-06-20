package microservice.educalle.catalogo.service;

import java.util.List;

import microservice.educalle.catalogo.dtos.CatalogoDto;

public interface CatalogoService {

   List<CatalogoDto> findAllCatalogosByGrupos(Integer grupo, Integer subgrupo);

}