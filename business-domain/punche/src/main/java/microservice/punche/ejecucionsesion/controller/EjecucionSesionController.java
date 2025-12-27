package microservice.punche.ejecucionsesion.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.punche.ejecucionsesion.dtos.CreateEjecucionSesionRequest;
import microservice.punche.ejecucionsesion.dtos.UpdateEjecucionSesionRequest;
import microservice.punche.ejecucionsesion.mappers.EjecucionSesionCreateMapper;
import microservice.punche.ejecucionsesion.mappers.EjecucionSesionUpdateMapper;
import microservice.punche.ejecucionsesion.model.EjecucionSesion;
import microservice.punche.ejecucionsesion.service.EjecucionSesionService;
import microservice.punche.programaciontaller.dtos.ProgramacionTallerResponse;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import java.io.IOException;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class EjecucionSesionController {

      private final EjecucionSesionService service;
      private final EjecucionSesionCreateMapper createMapper;
      private final EjecucionSesionUpdateMapper updateMapper;

      @PostMapping(path = { "/createEjecucionSesion" })
      public ResponseEntity<ApiResponse<EjecucionSesion>> createEjecucionSesion(
                  @RequestBody @Valid CreateEjecucionSesionRequest ejecucionSesionRequest) {
            return ResponseEntity.ok(
                        ApiResponse.<EjecucionSesion>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.createEjecucionSesion(
                                                this.createMapper.toModel(ejecucionSesionRequest)))
                                    .build());
      }

      @PutMapping(path = { "/updateEjecucionSesion" })
      public ResponseEntity<ApiResponse<EjecucionSesion>> updateEjecucionSesion(
                  @RequestBody @Valid UpdateEjecucionSesionRequest ejecucionSesionRequest) {
            return ResponseEntity.ok(
                        ApiResponse.<EjecucionSesion>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service
                                                .updateEjecucionSesion(
                                                            this.updateMapper.toModel(ejecucionSesionRequest)))
                                    .build());
      }

      @DeleteMapping(path = { "/deleteEjecucionSesionById" })
      public ResponseEntity<ApiResponse<Void>> deleteEjecucionSesionById(@RequestParam Long idEjecucionSesion) {
            this.service.deleteEjecucionSesionById(idEjecucionSesion);
            ApiResponseStatus apiResStatus = ApiResponseStatus.SUCCESS_DELETE;
            apiResStatus.setMessage(idEjecucionSesion);

            return ResponseEntity.ok(
                        ApiResponse.<Void>builder().message(apiResStatus.getMessage()).build());
      }

      @PutMapping(path = { "/uploadAnexoEjecucionSesion" })
      public ResponseEntity<ApiResponse<Void>> uploadAnexoEjecucionSesion(@RequestParam Long idEjecSesion,
                  @RequestParam MultipartFile anexo) throws IOException {
            this.service.uploadAnexoEjecucionSesion(idEjecSesion, anexo.getOriginalFilename(), anexo.getBytes());
            return ResponseEntity.ok(
                        ApiResponse
                                    .<Void>builder()
                                    .message(ApiResponseStatus.SUCCESS_UPLOAD_FILE.getMessage())
                                    .build());
      }

      @GetMapping(path = { "/downloadAnexoEjecucionSesion" })
      public ResponseEntity<byte[]> downloadAnexoEjecucionSesion(@RequestParam Long idEjecSesion) {
            EjecucionSesion ejecucionSesion = this.service.findEjecucionSesionById(idEjecSesion);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            header.setContentDisposition(
                        ContentDisposition.builder("attachment").filename(ejecucionSesion.getAnexoNombre()).build());
            return ResponseEntity.ok()
                        .headers(header)
                        .body(ejecucionSesion.getAnexo());

      }

}
