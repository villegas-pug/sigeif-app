package microservice.educalle.programaciontaller.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.educalle.programaciontaller.dtos.CreateProgramacionTallerRequest;
import microservice.educalle.programaciontaller.dtos.ProgramacionTallerResponse;
import microservice.educalle.programaciontaller.dtos.UpdateProgramacionTallerRequest;
import microservice.educalle.programaciontaller.mappers.ProgramacionTallerCreateMapper;
import microservice.educalle.programaciontaller.mappers.ProgramacionTallerResponseMapper;
import microservice.educalle.programaciontaller.mappers.ProgramacionTallerUpdateMapper;
import microservice.educalle.programaciontaller.model.ProgramacionTaller;
import microservice.educalle.programaciontaller.service.ProgramacionTallerService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.dtos.responses.ProgramacionTallerProjectionResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import java.io.IOException;
import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@Valid
public class ProgramacionTallerController {

      private final ProgramacionTallerService service;
      private final ProgramacionTallerCreateMapper createMapper;
      private final ProgramacionTallerUpdateMapper updateMapper;

      @PostMapping(path = { "/createProgramacionTaller" })
      public ResponseEntity<ApiResponse<ProgramacionTaller>> createProgramacionTaller(
                  @RequestBody @Valid CreateProgramacionTallerRequest programacionTaller) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<ProgramacionTaller>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.createProgramacionTaller(
                                                this.createMapper.toModel(programacionTaller)))
                                    .build());
      }

      @PutMapping(path = { "/updateProgramacionTaller" })
      public ResponseEntity<ApiResponse<ProgramacionTaller>> updateProgramacionTaller(
                  @RequestBody @Valid UpdateProgramacionTallerRequest programacionTaller) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<ProgramacionTaller>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service
                                                .updateProgramacionTaller(
                                                            this.updateMapper.toModel(programacionTaller)))
                                    .build());
      }

      @GetMapping(path = { "/findProgramacionTallerById" })
      public ResponseEntity<ApiResponse<ProgramacionTallerResponse>> findProgramacionTallerById(
                  @RequestParam Long idProgTaller) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<ProgramacionTallerResponse>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findProgramacionTallerById(idProgTaller))
                                    .build());
      }

      @GetMapping(path = { "/findProgramacionTalleresByParams" })
      public ResponseEntity<ApiResponse<List<ProgramacionTallerProjectionResponse>>> findProgramacionTalleresByParams(
                  @RequestParam Integer idServicio, @RequestParam Integer anio,
                  @RequestParam Integer mes) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<ProgramacionTallerProjectionResponse>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findProgramacionTalleresByParams(idServicio, anio, mes))
                                    .build());
      }

      @DeleteMapping(path = { "/deleteProgramacionTallerById" })
      public ResponseEntity<ApiResponse<Void>> deleteProgramacionTallerById(@RequestParam Long idProgTaller) {
            ApiResponseStatus apiStatus = ApiResponseStatus.SUCCESS_DELETE;
            apiStatus.setMessage(idProgTaller);
            this.service.deleteProgramacionTallerById(idProgTaller);
            return ResponseEntity.ok(
                        ApiResponse
                                    .<Void>builder()
                                    .message(apiStatus.getMessage())
                                    .build());
      }

      @PutMapping(path = { "/uploadAnexoProgramacionTaller" })
      public ResponseEntity<ApiResponse<Void>> uploadAnexoProgramacionTaller(@RequestParam Long idProgTaller,
                  @RequestParam MultipartFile anexo) throws IOException {
            this.service.uploadAnexoProgramacionTaller(idProgTaller, anexo.getOriginalFilename(), anexo.getBytes());
            return ResponseEntity.ok(
                        ApiResponse
                                    .<Void>builder()
                                    .message(ApiResponseStatus.SUCCESS_UPLOAD_FILE.getMessage())
                                    .build());
      }

      @GetMapping(path = { "/downloadAnexoProgramacionTaller" })
      public ResponseEntity<byte[]> downloadAnexoProgramacionTaller(@RequestParam Integer idProgTaller) {
            ProgramacionTaller programacionTaller = this.service.findProgramacionTallerById(idProgTaller);
            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            header.setContentDisposition(
                        ContentDisposition.builder("attachment").filename(programacionTaller.getAnexoNombre()).build());
            return ResponseEntity.ok()
                        .headers(header)
                        .body(programacionTaller.getAnexo());

      }

}
