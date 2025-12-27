package microservice.cedif.domain.ports.in.anexorespuesta;

import java.util.List;
import microservice.cedif.domain.models.AnexoRespuesta;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.querys.AnexoRespuestaQuery;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;

public interface AnexoRespuestaServicePort {

      AnexoRespuesta evaluarAnexoPreguntas(AnexoRespuesta anexoRespuesta);

      <M> List<M> updateAnexosRespuestas(List<AnexoRespuesta> anexosRespuestas);

      <M> List<M> createAnexosRespuestas(List<AnexoRespuesta> anexosRespuestas);

      List<AnexoRespuestaQuery> findAnexosRespuestasByQuerys(GetAnexoRespuestaByParamsQuery query);

      <M, Q> List<M> findIntegranteAnexosRespuestasByQuerys(GetIntegranteAnexoRespuestaByParamsQuery query);

      AnexoRespuesta findAnexoRespuestaById(Long idRespuesta);

      void uploadAnexoRespuesta(AnexoRespuesta anexoRespuesta);

      List<EstadoAnexoProjectionResponse> findEstadosAnexosByParams(Long idFamilia, Long idIntegrante);

      List<ReporteComparativoFasesFichaProjection> generateComparativeReportForFichaFasesByFilters(
                  Integer numAnexo, Long idFamilia, Long idIntegrante);

      void deleteAnexoRespuestasByParams(Integer numAnexo, Integer fase, Long idFamilia, Long idIntegrante);

}