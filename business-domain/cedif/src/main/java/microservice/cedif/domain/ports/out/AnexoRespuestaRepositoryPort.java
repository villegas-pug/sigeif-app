package microservice.cedif.domain.ports.out;

import java.util.List;
import java.util.Optional;
import microservice.cedif.domain.models.AnexoRespuesta;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.querys.AnexoRespuestaQuery;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;

public interface AnexoRespuestaRepositoryPort {

      AnexoRespuesta save(AnexoRespuesta respuesta);

      <M> List<M> saveAll(List<AnexoRespuesta> respuesta);

      List<AnexoRespuestaQuery> findAnexosRespuestasByQuerys(Integer idFamilia, Integer anexo, Integer grupo);

      <M, Q> List<M> findIntegranteAnexosRespuestasByQuerys(Integer idIntegrante, Integer anexo, Integer grupo);

      Optional<AnexoRespuesta> findAnexoRespuestaById(Long idRespuesta);

      List<EstadoAnexoProjectionResponse> findEstadosAnexosByParams(Long idFamilia, Long idIntegrante);

      List<ReporteComparativoFasesFichaProjection> generateComparativeReportForFichaFasesByFilters(
                  Integer numAnexo, Long idFamilia, Long idIntegrante);

      void deleteAnexoRespuestasByParams(Integer numAnexo, Integer fase, Long idFamilia, Long idIntegrante);

}
