package microservice.sigesu.potencialfamilia.controller;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.sigesu.potencialfamilia.dtos.CreatePotencialFamiliaRequest;
import microservice.sigesu.potencialfamilia.dtos.PotencialFamiliaResponse;
import microservice.sigesu.potencialfamilia.dtos.UpdatePartialPotecialFamiliaRequest;
import microservice.sigesu.potencialfamilia.dtos.UpdatePotencialFamiliaRequest;
import microservice.sigesu.potencialfamilia.model.PotencialFamilia;
import microservice.sigesu.potencialfamilia.service.PotencialFamiliaService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.dtos.responses.PotencialFamiliaWithEstadoAnexosResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@AllArgsConstructor
public class PotencialFamiliaController {

      private final PotencialFamiliaService service;

      @PostMapping(path = { "/createPotecialFamilia" })
      public ResponseEntity<?> createPotecialFamilia(
                  @RequestBody @Valid CreatePotencialFamiliaRequest potencialFamiliaRequet) {
            this.service.createPotecialFamilia(potencialFamiliaRequet);
            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).build());
      }

      @GetMapping(path = { "/findPotencialFamiliaById" })
      public ResponseEntity<?> findPotencialFamiliaById(@RequestParam Long idFamilia) {
            PotencialFamiliaResponse potencialFamilia = this.service.findPotencialFamiliaById(idFamilia);
            return ResponseEntity.ok(
                        ApiResponse.builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(potencialFamilia)
                                    .build());
      }

      @PutMapping(path = { "/updatePotencialFamilia" })
      public ResponseEntity<?> updatePotencialFamilia(
                  @RequestBody @Valid UpdatePotencialFamiliaRequest potencialFamiliaRequest) {
            this.service.updatePotencialFamilia(potencialFamiliaRequest);
            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage())
                                    .build());
      }

      @DeleteMapping(path = { "/deletePotencialFamiliaById" })
      public ResponseEntity<?> deletePotencialFamiliaById(@RequestParam Long idFamilia) {
            this.service.deletePotencialFamiliaById(idFamilia);
            ApiResponseStatus responseStatus = ApiResponseStatus.SUCCESS_DELETE;
            responseStatus.setMessage(idFamilia);
            return ResponseEntity.ok(
                        ApiResponse.builder().message(responseStatus.getMessage()).build());
      }

      @GetMapping(path = { "/findPotencialFamiliaWithEstadoAnexosResponseByIdFamilia" })
      public ResponseEntity<ApiResponse<PotencialFamiliaWithEstadoAnexosResponse>> findPotencialFamiliaWithEstadoAnexosResponseByIdFamilia(
                  @RequestParam Long idFamilia) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<PotencialFamiliaWithEstadoAnexosResponse>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service
                                                .findPotencialFamiliaWithEstadoAnexosResponseByIdFamilia(idFamilia))
                                    .build());
      }

      @PatchMapping(path = { "/updatePartialPotecialFamilia" })
      public ResponseEntity<ApiResponse<PotencialFamilia>> updatePartialPotecialFamilia(
                  @RequestBody @Valid UpdatePartialPotecialFamiliaRequest potencialFamilia) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<PotencialFamilia>builder()
                                    .message(ApiResponseStatus.SUCCESS_UPDATE.getMessage())
                                    .data(this.service.partialUpdatePotecialFamilia(potencialFamilia))
                                    .build());

      }
}
