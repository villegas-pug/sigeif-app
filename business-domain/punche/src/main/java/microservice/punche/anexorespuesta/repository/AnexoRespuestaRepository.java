package microservice.punche.anexorespuesta.repository;

import java.util.List;
import java.util.Optional;

import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;

public interface AnexoRespuestaRepository {

      AnexoRespuesta save(AnexoRespuesta respuesta);

      <M> List<M> saveAll(List<AnexoRespuesta> respuesta);

      <M, Q> List<M> findAnexosRespuestasByQuerys(Integer idFamilia, Integer anexo, Integer grupo);

      <M, Q> List<M> findIntegranteAnexosRespuestasByQuerys(Integer idIntegrante, Integer anexo, Integer grupo);

      Optional<AnexoRespuesta> findAnexoRespuestaById(Long idRespuesta);

      List<EstadoAnexoProjectionResponse> findEstadosAnexosByParams(Long idFamilia, Long idIntegrante);

      List<ReporteComparativoFasesFichaProjection> generateComparativeReportForFichaFasesByFilters(
                  Integer numAnexo, Long idFamilia, Long idIntegrante);

      void deleteAnexoRespuestasByParams(Integer numAnexo, Integer fase, Long idFamilia, Long idIntegrante);

}
