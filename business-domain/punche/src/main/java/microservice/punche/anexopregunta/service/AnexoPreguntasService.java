package microservice.punche.anexopregunta.service;

import java.util.List;

import microservice.punche.anexopregunta.dtos.AnexoPregutasDto;

public interface AnexoPreguntasService {

   List<AnexoPregutasDto> findAllAnexoPregustasByParams(Integer idServicio, Integer anexo, Integer grupo);

}