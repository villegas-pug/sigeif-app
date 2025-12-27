package microservice.punche.anexorespuesta.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.punche.anexopregunta.model.AnexoPregunta;
import microservice.punche.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.punche.anexorespuesta.dtos.UpdateAnexoRespuestaRequest;
import microservice.punche.anexorespuesta.mappers.AnexoRespuestaUpdateMapper;
import microservice.punche.anexorespuesta.dtos.GetAnexoRespuestaByParamsQuery;
import microservice.punche.anexorespuesta.dtos.GetIntegranteAnexoRespuestaByParamsQuery;
import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.punche.anexorespuesta.service.AnexoRespuestaService;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.io.IOException;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@Validated
public class AnexoRespuestaController {

      private final AnexoRespuestaService service;
      private final AnexoRespuestaUpdateMapper updateMapper;

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

}
