package microservice.sigesu.anexorespuesta.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import microservice.sigesu.anexorespuesta.dtos.AnexoCabeceraResponse;
import microservice.sigesu.anexorespuesta.dtos.AnexoEvaluacionResponse;
import microservice.sigesu.anexorespuesta.dtos.CreateAnexoEvaluacionRequest;
import microservice.sigesu.anexorespuesta.dtos.UpdateAnexoCompletoRequest;
import microservice.sigesu.anexorespuesta.model.AnexoRespuesta;
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

      AnexoEvaluacionResponse crearAnexoCompleto(CreateAnexoEvaluacionRequest request);

      List<AnexoCabeceraResponse> listarAnexosCabecera();

      Map<String, Object> obtenerRespuestasPorAnexo(Long idAnexoCabecera, Integer correlativo);

      AnexoEvaluacionResponse updateAnexoCompleto(UpdateAnexoCompletoRequest request);

      void actualizarAudio(Long idAnexoCabecera, String audioUrl);

      List<Map<String, Object>> listarResponsablesSupervision(String abreviatura);

      List<Map<String, Object>> listarResponsablesCentro(String nombreCentro);

      void savePersonalValidaAnexoCabecera(Integer idCabecera, String idsPersonal);

      void saveConformidadAnexoCabecera(Integer idCabecera, Integer estado);

      void insertarAnexoCabeceraAudio(Long idAnexoCabecera, byte[] audio, String nombreArchivo);

      void actualizarAnexoCabeceraAudio(Long idAudio, byte[] audio, String nombreArchivo, Integer estado);

      void eliminarAnexoCabeceraAudio(Long idAudio);

      List<Map<String, Object>> consultarAnexoCabeceraAudio(Long idAnexoCabecera);

      List<Map<String, Object>> listarAnexoCabeceraAudio(Long idAnexoCabecera);
}
