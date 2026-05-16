package microservice.punche.unidadtema.controller;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.punche.unidadtema.dtos.CreateUnidadTemaRequest;
import microservice.punche.unidadtema.dtos.UpdateUnidadTemaRequest;
import microservice.punche.unidadtema.mappers.UnidadTemaCreateMapper;
import microservice.punche.unidadtema.mappers.UnidadTemaUpdateMapper;
import microservice.punche.unidadtema.model.UnidadTema;
import microservice.punche.unidadtema.service.UnidadTemaService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@Valid
public class UnidadTemaController {

      private final UnidadTemaService service;
      private final UnidadTemaCreateMapper createMapper;
      private final UnidadTemaUpdateMapper updateMapper;

      @PostMapping(path = { "/createUnidadTema" })
      public ResponseEntity<ApiResponse<UnidadTema>> createUnidadTema(
                  @RequestBody @Valid CreateUnidadTemaRequest unidadSesionRequest) {
            UnidadTema model = this.service.createUnidadTema(this.createMapper.toModel(unidadSesionRequest));
            return ResponseEntity.ok(
                        ApiResponse.<UnidadTema>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(model)
                                    .build());
      }

      @PutMapping(path = { "/updateUnidadTema" })
      public ResponseEntity<ApiResponse<UnidadTema>> updateUnidadTema(
                  @RequestBody @Valid UpdateUnidadTemaRequest unidadSesionRequest) {
            UnidadTema model = this.service.updateUnidadTema(this.updateMapper.toModel(unidadSesionRequest));
            return ResponseEntity.ok(
                        ApiResponse.<UnidadTema>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(model)
                                    .build());
      }

      @GetMapping(path = { "/findAllTemaByIdUnidad" })
      public ResponseEntity<ApiResponse<List<UnidadTema>>> findAllTemaByIdUnidad(@RequestParam Integer idUnidad) {
            List<UnidadTema> model = this.service.findAllTemaByIdUnidad(idUnidad);
            return ResponseEntity.ok(
                        ApiResponse.<List<UnidadTema>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(model)
                                    .build());
      }

}
