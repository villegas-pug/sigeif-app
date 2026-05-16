package microservice.sigesu.centroreferencia.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.sigesu.centroreferencia.dtos.CentroRefereciaCreateRequestDto;
import microservice.sigesu.centroreferencia.service.CentroReferenciaService;
import microservice.sigesu.institucion.dtos.InstitucionCreateRequestDto;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class CentroReferenciaController {

      private final CentroReferenciaService service;

      @GetMapping(path = { "/findCentrosReferenciaByTipo" })
      public ResponseEntity<ApiResponse<?>> findCentrosReferenciaByTipo(@RequestParam int idTipo) {
            return ResponseEntity.ok().body(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findCentrosReferenciaByTipo(idTipo))
                                    .build());
      }

      @GetMapping(path = { "/findCentroReferenciaByNombreContaining" })
      public ResponseEntity<ApiResponse<?>> findCentroReferenciaByNombreContaining(@RequestParam int idTipo,
                  @RequestParam String ref) {
            return ResponseEntity.ok().body(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findCentroReferenciaByNombreContaining(idTipo, ref))
                                    .build());
      }

      @GetMapping(path = { "/findAllDepartamentos" })
      public ResponseEntity<ApiResponse<?>> findAllDepartamentos() {
            return ResponseEntity.ok().body(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findAllDepartamentos())
                                    .build());
      }

      @GetMapping(path = { "/findAllProvinciasPorDepartamento" })
      public ResponseEntity<ApiResponse<?>> findAllProvinciasPorDepartamento(@RequestParam String idUbigeo) {
            return ResponseEntity.ok().body(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findAllProvinciasPorDepartamento(idUbigeo))
                                    .build());
      }

      @GetMapping(path = { "/findAllDistritosPorProvincia" })
      public ResponseEntity<ApiResponse<?>> findAllDistritosPorProvincia(@RequestParam String idUbigeo) {
            return ResponseEntity.ok().body(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findAllDistritosPorProvincia(idUbigeo))
                                    .build());
      }

      @PostMapping(path = { "/createInstitucion" })
      public ResponseEntity<?> createInstitucion(@Valid @RequestBody CentroRefereciaCreateRequestDto centroReferecia) {
            this.service.saveCentroReferencia(2, centroReferecia);
            return ResponseEntity.ok().body(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .build());
      }

}
