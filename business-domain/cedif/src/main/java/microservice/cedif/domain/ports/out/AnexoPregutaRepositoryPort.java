package microservice.cedif.domain.ports.out;

import java.util.List;

import microservice.cedif.domain.models.AnexoPregunta;

public interface AnexoPregutaRepositoryPort {

   List<AnexoPregunta> findAllAnexoPregustasByParams(Integer idServicio, Integer anexo, Integer grupo);

}
