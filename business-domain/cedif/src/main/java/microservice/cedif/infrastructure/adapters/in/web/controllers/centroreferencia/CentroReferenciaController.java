package microservice.cedif.infrastructure.adapters.in.web.controllers.centroreferencia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.ports.in.centroreferencia.CentroReferenciaCreateCommand;
import microservice.cedif.domain.ports.in.centroreferencia.CentroReferenciaServicePort;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class CentroReferenciaController {

      private final CentroReferenciaServicePort service;
      private final CentroReferenciaCommandMapper createMapper;

      @GetMapping(path = { "/findCentrosReferenciaByTipo" })
      public ResponseEntity<ApiResponse<?>> findCentrosReferenciaByTipo(@RequestParam int idTipo) {
            return ResponseEntity.ok().body(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findCentrosReferenciaByTipo(idTipo))
                                    .build());
      }

      @GetMapping(path = { "/findCentrosReferenciaByParams" })
      public ResponseEntity<ApiResponse<?>> findCentrosReferenciaByParams(
                  @RequestParam int idTipo,
                  @RequestParam Integer anio,
                  @RequestParam Integer mes,
                  @RequestParam String ref) {
            return ResponseEntity.ok().body(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findCentrosReferenciaByParams(idTipo, anio, mes, ref))
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

      @PostMapping(path = { "/saveCentroReferencia/{tipoRef}" })
      public ResponseEntity<?> saveCentroReferencia(@PathVariable int tipoRef,
                  @RequestBody @Valid CentroReferenciaCreateRequest centroReferecia) {

            CentroReferenciaCreateCommand command = this.createMapper.toCreate(centroReferecia);
            this.service.saveCentroReferencia(tipoRef, command);

            return ResponseEntity.ok().body(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .build());
      }

}
