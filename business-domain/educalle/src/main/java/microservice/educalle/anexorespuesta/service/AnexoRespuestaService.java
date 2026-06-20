package microservice.educalle.anexorespuesta.service;

import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;
import microservice.educalle.anexorespuesta.dtos.AnexoCabeceraResponse;
import microservice.educalle.anexorespuesta.dtos.AnexoEvaluacionResponse;
import microservice.educalle.anexorespuesta.dtos.CreateAnexoEvaluacionRequest;
import microservice.educalle.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.educalle.anexorespuesta.dtos.UpdateAnexoRespuestaRequest;
import microservice.educalle.anexorespuesta.dtos.GetAnexoRespuestaByParamsQuery;
import microservice.educalle.anexorespuesta.dtos.GetIntegranteAnexoRespuestaByParamsQuery;
import microservice.educalle.anexorespuesta.dtos.UpdateAnexoCompletoRequest;
import microservice.educalle.anexorespuesta.model.AnexoRespuesta;
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

      AnexoEvaluacionResponse crearAnexoCompleto(CreateAnexoEvaluacionRequest request);

      List<AnexoCabeceraResponse> listarAnexosCabecera();

      Map<String, Object> obtenerRespuestas(Long idAnexoCabecera, Integer correlativo);

      AnexoEvaluacionResponse updateAnexoCompleto(UpdateAnexoCompletoRequest request);

      byte[] generarPdf(Long idAnexoCabecera, Integer correlativo);

      void guardarAudio(MultipartFile file, Long idAnexoCabecera);

      List<Map<String, Object>> listarResponsablesSupervision(String abreviatura);

      List<Map<String, Object>> listarResponsablesCentro(String nombreCentro, Long idUnidadOrganica);

      boolean verifyPersonal(Integer idPersonal, String password);

      void validatePersonalAnexoCabecera(Integer idAnexoCabecera, String idPersonal, String password);

      void saveConformidadAnexoCabecera(Integer idCabecera, Integer estado);

      void resetValidacionAnexoCabecera(Integer idCabecera);

      void insertarAnexoCabeceraAudio(Long idAnexoCabecera, byte[] audio, String nombreArchivo);

      void actualizarAnexoCabeceraAudio(Long idAudio, byte[] audio, String nombreArchivo, Integer estado);

      void eliminarAnexoCabeceraAudio(Long idAudio);

      List<Map<String, Object>> consultarAnexoCabeceraAudio(Long idAnexoCabecera);

      List<Map<String, Object>> listarAnexoCabeceraAudio(Long idAnexoCabecera);

}