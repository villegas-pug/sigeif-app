package microservice.educalle.unidadsesion.controller;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.educalle.unidadsesion.dtos.CreateUnidadSesionRequest;
import microservice.educalle.unidadsesion.dtos.UpdateUnidadSesionRequest;
import microservice.educalle.unidadsesion.mappers.UnidadSesionCreateMapper;
import microservice.educalle.unidadsesion.mappers.UnidadSesionUpdateMapper;
import microservice.educalle.unidadsesion.model.UnidadSesion;
import microservice.educalle.unidadsesion.service.UnidadSesionService;
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
public class UnidadSesionController {

      private final UnidadSesionService service;
      private final UnidadSesionCreateMapper createMapper;
      private final UnidadSesionUpdateMapper updateMapper;

      @PostMapping(path = { "/createUnidadSesion" })
      public ResponseEntity<ApiResponse<UnidadSesion>> createUnidadSesion(
                  @RequestBody @Valid CreateUnidadSesionRequest unidadSesionRequest) {
            UnidadSesion model = this.service.createUnidadSesion(this.createMapper.toModel(unidadSesionRequest));
            return ResponseEntity.ok(
                        ApiResponse.<UnidadSesion>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(model)
                                    .build());
      }

      @PutMapping(path = { "/updateUnidadSesion" })
      public ResponseEntity<ApiResponse<UnidadSesion>> updateUnidadSesion(
                  @RequestBody @Valid UpdateUnidadSesionRequest unidadSesionRequest) {
            UnidadSesion model = this.service.updateUnidadSesion(this.updateMapper.toModel(unidadSesionRequest));
            return ResponseEntity.ok(
                        ApiResponse.<UnidadSesion>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(model)
                                    .build());
      }

      @GetMapping(path = { "/findAllSesionByIdUnidad" })
      public ResponseEntity<ApiResponse<List<UnidadSesion>>> findAllSesionByIdUnidad(@RequestParam Integer idUnidad) {
            List<UnidadSesion> model = this.service.findAllSesionByIdUnidad(idUnidad);
            return ResponseEntity.ok(
                        ApiResponse.<List<UnidadSesion>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(model)
                                    .build());
      }

}
