package microservice.sigesu.anexopregunta.service;

import java.util.List;

import microservice.sigesu.anexopregunta.dtos.AnexoPregutasDto;

public interface AnexoPreguntasService {

   List<AnexoPregutasDto> findAllAnexoPregustasByParams(Integer idServicio, Integer anexo, Integer grupo);
   List<AnexoPregutasDto> findAllAnexoPregustasByParams2(Integer idServicio, Integer anexo, Integer grupo);


}