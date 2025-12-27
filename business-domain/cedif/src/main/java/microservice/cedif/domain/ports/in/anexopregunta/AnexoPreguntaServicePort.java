package microservice.cedif.domain.ports.in.anexopregunta;

import java.util.List;
import microservice.cedif.domain.models.AnexoPregunta;

public interface AnexoPreguntaServicePort {

   List<AnexoPregunta> findAllAnexoPregustasByParams(Integer idServicio, Integer anexo, Integer grupo);

   List<AnexoPregunta> findAllAnexoPregustasOfIntegranteByParams(Integer anexo, Long idIntegrante);
}