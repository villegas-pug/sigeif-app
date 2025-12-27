package microservice.punche.anexorespuesta.service;

import java.util.List;

import microservice.punche.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.punche.anexorespuesta.dtos.UpdateAnexoRespuestaRequest;
import microservice.punche.anexorespuesta.dtos.GetAnexoRespuestaByParamsQuery;
import microservice.punche.anexorespuesta.dtos.GetIntegranteAnexoRespuestaByParamsQuery;
import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;

public interface AnexoRespuestaService {

      AnexoRespuesta evaluarAnexoPreguntas(AnexoRespuesta anexoRespuesta);

      <M> List<M> updateAnexosRespuestas(List<UpdateAnexoRespuestaRequest> anexosRespuestas);

      <M> List<M> createAnexosRespuestas(List<CreateAnexoRespuestaRequest> anexosRespuestas);

      <M> List<M> findAnexosRespuestasByQuerys(GetAnexoRespuestaByParamsQuery query);

      <M, Q> List<M> findIntegranteAnexosRespuestasByQuerys(GetIntegranteAnexoRespuestaByParamsQuery query);

      AnexoRespuesta findAnexoRespuestaById(Long idRespuesta);

      void uploadAnexoRespuesta(AnexoRespuesta anexoRespuesta);

      List<EstadoAnexoProjectionResponse> findEstadosAnexosByParams(Long idFamilia, Long idIntegrante);

      List<ReporteComparativoFasesFichaProjection> generateComparativeReportForFichaFasesByFilters(
                  Integer numAnexo, Long idFamilia, Long idIntegrante);

      void deleteAnexoRespuestasByParams(Integer numAnexo, Integer fase, Long idFamilia, Long idIntegrante);

}