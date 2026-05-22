package microservice.sigesu.anexorespuesta.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.sigesu.anexopregunta.model.AnexoPregunta;
import microservice.sigesu.anexorespuesta.dtos.AnexoCabeceraResponse;
import microservice.sigesu.anexorespuesta.dtos.AnexoEvaluacionResponse;
import microservice.sigesu.anexorespuesta.dtos.CreateAnexoEvaluacionRequest;
import microservice.sigesu.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.sigesu.anexorespuesta.dtos.UpdateAnexoRespuestaRequest;
import microservice.sigesu.anexorespuesta.mappers.AnexoRespuestaUpdateMapper;
import microservice.sigesu.anexorespuesta.dtos.GetAnexoRespuestaByParamsQuery;
import microservice.sigesu.anexorespuesta.dtos.GetIntegranteAnexoRespuestaByParamsQuery;
import microservice.sigesu.anexorespuesta.dtos.UpdateAnexoCompletoRequest;
import microservice.sigesu.anexorespuesta.model.AnexoRespuesta;
import microservice.sigesu.anexorespuesta.service.AnexoRespuestaService;
import microservice.sigesu.familiaintegrante.model.FamiliaIntegrante;
import microservice.sigesu.potencialfamilia.model.PotencialFamilia;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@Validated
public class AnexoRespuestaController {

      private final AnexoRespuestaService service;
      private final AnexoRespuestaUpdateMapper updateMapper;
      private final AnexoRespuestaService anexoRespuestaService;

      @PostMapping(path = { "/evaluarAnexoPreguntas" })
      public ResponseEntity<?> evaluarAnexoPreguntas(@RequestBody @Valid UpdateAnexoRespuestaRequest anexoRespuesta) {
            final AnexoRespuesta model = this.updateMapper.toModel(anexoRespuesta);
            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.evaluarAnexoPreguntas(model)).build());

      }

      @PostMapping(path = { "/createAnexosRespuestas" })
      public ResponseEntity<?> createAnexosRespuestas(
                  @RequestBody @Valid List<@Valid CreateAnexoRespuestaRequest> anexosRespuestas) {
            List<AnexoRespuesta> newAnexosRespuestas = this.service.createAnexosRespuestas(anexosRespuestas);

            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS_CREATE.getMessage()).data(
                                    newAnexosRespuestas).build());
      }

      @PutMapping(path = { "/updateAnexosRespuestas" })
      public ResponseEntity<?> updateAnexosRespuestas(
                  @RequestBody @Valid List<@Valid UpdateAnexoRespuestaRequest> anexosRespuestas) {
            final List<AnexoRespuesta> updatedAnexosRespuestas = this.service.updateAnexosRespuestas(anexosRespuestas);
            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(updatedAnexosRespuestas).build());

      }

      @GetMapping(path = { "/findAnexosRespuestasByQuerys" })
      public ResponseEntity<?> findAnexosRespuestasByQuerys(@RequestParam Integer idFamilia,
                  @RequestParam Integer anexo,
                  @RequestParam(required = false) Integer grupo) {

            List<?> resultsQuery = this.service.findAnexosRespuestasByQuerys(GetAnexoRespuestaByParamsQuery.builder()
                        .idFamilia(idFamilia)
                        .anexo(anexo)
                        .grupo(grupo)
                        .build());

            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(resultsQuery).build());
      }

      @GetMapping(path = { "/findIntegranteAnexosRespuestasByQuerys" })
      public ResponseEntity<?> findIntegranteAnexosRespuestasByQuerys(@RequestParam Integer idIntegrante,
                  @RequestParam Integer anexo,
                  @RequestParam(required = false) Integer grupo) {

            var resultQuery = this.service
                        .findIntegranteAnexosRespuestasByQuerys(GetIntegranteAnexoRespuestaByParamsQuery.builder()
                                    .idIntegrante(idIntegrante)
                                    .anexo(anexo)
                                    .grupo(grupo)
                                    .build());

            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(resultQuery).build());
      }

      @PostMapping(path = { "/uploadAnexoRespuesta" }) // * New file
      public ResponseEntity<?> uploadAnexoRespuesta(@RequestParam MultipartFile archivo, @RequestParam Long idFamilia,
                  @RequestParam Long idPregunta,
                  @RequestParam Integer usuRegistra,
                  @RequestParam(required = false) Long idIntegrante) throws IOException {

            this.service.uploadAnexoRespuesta(
                        AnexoRespuesta
                                    .builder()
                                    .familia(PotencialFamilia.builder().idFamilia(idFamilia).build())
                                    .pregunta(AnexoPregunta.builder().idPregunta(idPregunta).build())
                                    .archivo(archivo.getBytes())
                                    .integrante(idIntegrante != null
                                                ? FamiliaIntegrante.builder().idIntegrante(idIntegrante).build()
                                                : null)
                                    .destinatario(idIntegrante != null ? 2 : 1) // * Destinatarios: Potencial familia ↔
                                    .respuesta(archivo.getOriginalFilename())
                                    .usuRegistra(usuRegistra)
                                    .build());

            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS_UPLOAD_FILE.getMessage()).build());
      }

      @PutMapping(path = { "/uploadAnexoRespuesta" }) // * Update file
      public ResponseEntity<?> uploadAnexoRespuesta(@RequestParam MultipartFile archivo,
                  @RequestParam Long idRespuesta,
                  @RequestParam Integer usuModifica) throws IOException {

            this.service.uploadAnexoRespuesta(
                        AnexoRespuesta
                                    .builder()
                                    .idRespuesta(idRespuesta)
                                    .respuesta(archivo.getOriginalFilename())
                                    .archivo(archivo.getBytes())
                                    .usuModifica(usuModifica)
                                    .build());

            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS_UPLOAD_FILE.getMessage()).build());
      }

      @GetMapping(path = { "/downloadAnexoRespuesta" })
      public ResponseEntity<byte[]> downloadAnexoRespuesta(@RequestParam Long idRespuesta) {

            AnexoRespuesta anexoRespuesta = this.service.findAnexoRespuestaById(idRespuesta);

            var headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + anexoRespuesta.getRespuesta() + "\"");

            return ResponseEntity.ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(anexoRespuesta.getArchivo());

      }

      @GetMapping(path = { "/findEstadosAnexosByParams" })
      public ResponseEntity<ApiResponse<List<EstadoAnexoProjectionResponse>>> findEstadosAnexosByParams(
                  @RequestParam(required = false) Long idFamilia,
                  @RequestParam(required = false) Long idIntegrante) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<EstadoAnexoProjectionResponse>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service
                                                .findEstadosAnexosByParams(idFamilia, idIntegrante))
                                    .build());
      }

      @GetMapping(path = { "/generateComparativeReportForFichaFasesByParams" })
      public ResponseEntity<ApiResponse<List<ReporteComparativoFasesFichaProjection>>> generateComparativeReportForFichaFasesByParams(
                  @RequestParam Integer numAnexo,
                  @RequestParam(required = false) Long idFamilia,
                  @RequestParam(required = false) Long idIntegrante) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<ReporteComparativoFasesFichaProjection>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.generateComparativeReportForFichaFasesByFilters(numAnexo,
                                                idFamilia, idIntegrante))
                                    .build());
      }

      @DeleteMapping(path = { "/deleteAnexoRespuestasByParams" })
      public ResponseEntity<ApiResponse<Void>> deleteAnexoRespuestasByParams(
                  @RequestParam Integer numAnexo,
                  @RequestParam Integer idFase,
                  @RequestParam(required = false) Long idFamilia,
                  @RequestParam(required = false) Long idIntegrante) {
            this.service.deleteAnexoRespuestasByParams(numAnexo, idFase, idFamilia, idIntegrante);
            return ResponseEntity.ok(
                        ApiResponse
                                    .<Void>builder()
                                    .message(ApiResponseStatus.SUCCESS_DELETE_ALL.getMessage())
                                    .build());
      }

      @PostMapping("/createAnexoCompleto")
      public ResponseEntity<AnexoEvaluacionResponse> crear(
                  @Valid @RequestBody CreateAnexoEvaluacionRequest request) {

            return ResponseEntity.ok(service.crearAnexoCompleto(request));
      }

      @GetMapping("/listarAnexosCabecera")
      public ResponseEntity<ApiResponse<List<AnexoCabeceraResponse>>> listarAnexosCabecera() {

            return ResponseEntity.ok(
                        ApiResponse.<List<AnexoCabeceraResponse>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(service.listarAnexosCabecera())
                                    .build());
      }

      @GetMapping("/obtenerRespuestas")
      public ResponseEntity<ApiResponse<Map<String, Object>>> obtenerRespuestas(
                  @RequestParam Long idAnexoCabecera,
                  @RequestParam Integer correlativo) {
            try {
                  Map<String, Object> result = service.obtenerRespuestas(idAnexoCabecera, correlativo);

                  if (result.isEmpty() || result.get("idAnexoCabecera") == null) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body(ApiResponse.<Map<String, Object>>builder()
                                                .message(ApiResponseStatus.INTERNAL_SERVER_ERROR.getMessage())
                                                .data(null)
                                                .build());
                  }

                  return ResponseEntity.ok(
                              ApiResponse.<Map<String, Object>>builder()
                                          .message(ApiResponseStatus.SUCCESS.getMessage())
                                          .data(result)
                                          .build());
            } catch (Exception e) {
                  e.printStackTrace();
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                              .body(ApiResponse.<Map<String, Object>>builder()
                                          .message(ApiResponseStatus.INTERNAL_SERVER_ERROR.getMessage())
                                          .data(null)
                                          .build());
            }
      }

      @PutMapping("/updateAnexoCompleto")
      public ResponseEntity<AnexoEvaluacionResponse> actualizarAnexoCompleto(
                  @RequestBody UpdateAnexoCompletoRequest request) {

            AnexoEvaluacionResponse response = service.updateAnexoCompleto(request);
            return ResponseEntity.ok(response);
      }

      @GetMapping("/anexo/pdf")
      public ResponseEntity<byte[]> generarPdf(
                  @RequestParam Long idAnexoCabecera,
                  @RequestParam Integer correlativo) {

            byte[] pdf = service
                        .generarPdf(idAnexoCabecera, correlativo);

            return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                    "inline; filename=anexo.pdf")
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(pdf);
      }

      @PostMapping("/anexo/upload-audio")
      public ResponseEntity<?> uploadAudio(
                  @RequestParam("audio") MultipartFile file,
                  @RequestParam("idAnexoCabecera") Long id) {

            if (file == null || file.isEmpty()) {
                  return ResponseEntity.badRequest()
                              .body("El archivo está vacío");
            }

            try {

                  anexoRespuestaService.guardarAudio(file, id);

                  return ResponseEntity.ok().body("Audio subido correctamente");

            } catch (Exception e) {

                  return ResponseEntity.internalServerError()
                              .body("Error al subir audio: " + e.getMessage());
            }
      }

      @GetMapping("/responsables-supervision")
      public ResponseEntity<?> listarResponsables() {

            return ResponseEntity.ok(
                        anexoRespuestaService.listarResponsablesSupervision(
                                    "UNIDAD DE FORTALECIMIENTO DE SERVICIOS Y COORDINACIÓN TERRITORIAL"));

      }

      @GetMapping("/responsables-centro")
      public ResponseEntity<?> listarResponsablesCentro(
                  @RequestParam(required = false) String nombreCentro,
                  @RequestParam(required = false) Long idUnidadOrganica) {

            return ResponseEntity.ok(
                        anexoRespuestaService.listarResponsablesCentro(nombreCentro, idUnidadOrganica));
      }

      @PatchMapping(path = { "/validatePersonalAnexoCabecera" })
      public ResponseEntity<?> validatePersonalAnexoCabecera(
                  @RequestParam Integer idAnexoCabecera,
                  @RequestParam Integer idPersonal,
                  @RequestParam String password) {

            this.service.validatePersonalAnexoCabecera(idAnexoCabecera, idPersonal, password);

            return ResponseEntity.ok(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(null).build());

      }

      @DeleteMapping(path = { "/resetValidacionAnexoCabecera" })
      public ResponseEntity<?> resetValidacionAnexoCabecera(@RequestParam Integer idAnexoCabecera) {

            this.service.resetValidacionAnexoCabecera(idAnexoCabecera);

            return ResponseEntity.ok(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS_DELETE.getMessage())
                                    .data(null).build());

      }

      @PatchMapping(path = { "/saveConformidadAnexoCabecera" })
      public ResponseEntity<?> saveConformidadAnexoCabecera(@RequestParam Integer idAnexoCabecera,
                  @RequestParam Integer estado) {
            this.service.saveConformidadAnexoCabecera(idAnexoCabecera, estado);
            return ResponseEntity.ok(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(null).build());

      }

      @PostMapping("/anexo-cabecera-audio")
      public ResponseEntity<?> insertarAnexoCabeceraAudio(
                  @RequestParam("audio") MultipartFile audio,
                  @RequestParam("idAnexoCabecera") Long idAnexoCabecera) {

            try {
                  this.service.insertarAnexoCabeceraAudio(idAnexoCabecera, audio.getBytes(),
                              audio.getOriginalFilename());
                  return ResponseEntity.ok(
                              ApiResponse.builder()
                                          .message(ApiResponseStatus.SUCCESS_CREATE.getMessage())
                                          .data(null)
                                          .build());
            } catch (Exception e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                              .body(ApiResponse.builder()
                                          .message(ApiResponseStatus.INTERNAL_SERVER_ERROR.getMessage())
                                          .data(e.getMessage())
                                          .build());
            }
      }

      @PutMapping("/anexo-cabecera-audio")
      public ResponseEntity<?> actualizarAnexoCabeceraAudio(
                  @RequestParam("audio") MultipartFile audio,
                  @RequestParam("idAudio") Long idAudio,
                  @RequestParam("estado") Integer estado) {

            try {
                  this.service.actualizarAnexoCabeceraAudio(idAudio, audio.getBytes(), audio.getOriginalFilename(),
                              estado);
                  return ResponseEntity.ok(
                              ApiResponse.builder()
                                          .message(ApiResponseStatus.SUCCESS.getMessage())
                                          .data(null)
                                          .build());
            } catch (Exception e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                              .body(ApiResponse.builder()
                                          .message(ApiResponseStatus.INTERNAL_SERVER_ERROR.getMessage())
                                          .data(e.getMessage())
                                          .build());
            }
      }

      @DeleteMapping("/anexo-cabecera-audio")
      public ResponseEntity<?> eliminarAnexoCabeceraAudio(
                  @RequestParam("idAudio") Long idAudio) {

            try {
                  this.service.eliminarAnexoCabeceraAudio(idAudio);
                  return ResponseEntity.ok(
                              ApiResponse.builder()
                                          .message(ApiResponseStatus.SUCCESS_DELETE.getMessage())
                                          .data(null)
                                          .build());
            } catch (Exception e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                              .body(ApiResponse.builder()
                                          .message(ApiResponseStatus.INTERNAL_SERVER_ERROR.getMessage())
                                          .data(e.getMessage())
                                          .build());
            }
      }

      @GetMapping("/anexo-cabecera-audio/listar")
      public ResponseEntity<?> listarAnexoCabeceraAudio(
                  @RequestParam("idAnexoCabecera") Long idAnexoCabecera) {

            try {
                  List<Map<String, Object>> resultados = this.service.listarAnexoCabeceraAudio(idAnexoCabecera);

                  return ResponseEntity.ok(
                              ApiResponse.builder()
                                          .message(ApiResponseStatus.SUCCESS.getMessage())
                                          .data(resultados)
                                          .build());

            } catch (Exception e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                              .body(ApiResponse.builder()
                                          .message(ApiResponseStatus.INTERNAL_SERVER_ERROR.getMessage())
                                          .data(e.getMessage())
                                          .build());
            }
      }

      @GetMapping("/anexo-cabecera-audio")
      public ResponseEntity<?> descargarAnexoCabeceraAudio(
                  @RequestParam("idAnexoCabecera") Long idAnexoCabecera,
                  @RequestParam("idAudio") Long idAudio) {

            try {
                  List<Map<String, Object>> resultados = this.service.consultarAnexoCabeceraAudio(idAnexoCabecera);

                  Map<String, Object> registro = resultados.stream()
                              .filter(r -> idAudio.equals(
                                          r.get("ACA_ID_AUDIO") instanceof Number
                                                      ? ((Number) r.get("ACA_ID_AUDIO")).longValue()
                                                      : null))
                              .findFirst()
                              .orElse(null);

                  if (registro == null) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .body(ApiResponse.builder()
                                                .message(ApiResponseStatus.NOT_FOUND.getMessage())
                                                .data(null)
                                                .build());
                  }

                  Object audioObj = registro.get("ACA_AUDIO");
                  byte[] audioBytes = extractBlobBytes(audioObj);
                  String nombreArchivo = (String) registro.get("ACA_NOMBRE_ARCHIVO");

                  if (nombreArchivo == null) {
                        nombreArchivo = "audio.mp3";
                  }

                  HttpHeaders headers = new HttpHeaders();
                  headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + nombreArchivo + "\"");

                  return ResponseEntity.ok()
                              .headers(headers)
                              .contentType(MediaType.APPLICATION_OCTET_STREAM)
                              .body(audioBytes);

            } catch (Exception e) {
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                              .body(ApiResponse.builder()
                                          .message(ApiResponseStatus.INTERNAL_SERVER_ERROR.getMessage())
                                          .data(e.getMessage())
                                          .build());
            }
      }

      private byte[] extractBlobBytes(Object blobValue) throws SQLException {
            if (blobValue == null) {
                  return new byte[0];
            }
            if (blobValue instanceof byte[]) {
                  return (byte[]) blobValue;
            }
            if (blobValue instanceof Blob) {
                  Blob blob = (Blob) blobValue;
                  return blob.getBytes(1, (int) blob.length());
            }
            throw new RuntimeException("Tipo de dato no soportado para el audio: " + blobValue.getClass().getName());
      }

}
