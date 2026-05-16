package microservice.cedif.infrastructure.adapters.in.web.controllers.potencialfamilia;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.PotencialFamilia;
import microservice.cedif.domain.ports.in.potencialfamilia.PotencialFamiliaCreateCommand;
import microservice.cedif.domain.ports.in.potencialfamilia.PotencialFamiliaServicePort;
import microservice.cedif.domain.ports.out.PotencialFamiliaRepositoryPort;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import java.util.List;

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

      private final PotencialFamiliaServicePort service;
      private final PotenciaFamiliaCommandMapper commandMapper;
      private final PotencialFamiliaModelMapper modelMapper;
      private final PotencialFamiliaUpdateMapper updateMapper;

      // ! Eliminar solo pruebas
      private final PotencialFamiliaRepositoryPort repository;

      @PostMapping(path = { "/createPotecialFamilia" })
      public ResponseEntity<?> createPotecialFamilia(
                  @RequestBody @Valid PotencialFamiliaCreateRequest potencialFamilia) {
            PotencialFamiliaCreateCommand command = this.commandMapper.toCreate(potencialFamilia);
            this.service.createPotecialFamilia(command);
            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).build());
      }

      @GetMapping(path = { "/findPotencialFamiliaById" })
      public ResponseEntity<?> findPotencialFamiliaById(@RequestParam Long idFamilia) {
            
            var potencialFamilia = this.service.findPotencialFamiliaById(idFamilia);

            System.out.println("hola mundo");

            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).data(potencialFamilia)
                                    .build());
      }

      @PutMapping(path = { "/updatePotencialFamilia" })
      public ResponseEntity<?> updatePotencialFamilia(
                  @RequestBody @Valid PotencialFamiliaUpdateRequest potencialFamilia) {
            PotencialFamilia updatedPotencialFamilia = this.service
                        .updatePotencialFamilia(this.modelMapper.fromUpdate(potencialFamilia));
            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(updatedPotencialFamilia)
                                    .build());
      }

      @DeleteMapping(path = { "/deletePotencialFamiliaById" })
      public ResponseEntity<?> deletePotencialFamiliaById(@RequestParam Long idFamilia) {
            var deletedPotencialFamilia = this.service.deletePotencialFamiliaById(idFamilia);
            ApiResponseStatus responseStatus = ApiResponseStatus.SUCCESS_DELETE;
            responseStatus.setMessage(idFamilia);
            return ResponseEntity.ok(
                        ApiResponse.builder().message(responseStatus.getMessage()).data(deletedPotencialFamilia)
                                    .build());
      }

      @PatchMapping(path = { "/updatePartialPotecialFamilia" })
      public ResponseEntity<ApiResponse<PotencialFamilia>> updatePartialPotecialFamilia(
                  @RequestBody @Valid UpdatePartialPotecialFamiliaRequest potencialFamilia) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<PotencialFamilia>builder()
                                    .message(ApiResponseStatus.SUCCESS_UPDATE.getMessage())
                                    .data(this.service.partialUpdatePotecialFamilia(
                                                this.updateMapper.toModel(potencialFamilia)))
                                    .build());

      }

      // ! Eliminar solo pruebas
      @GetMapping(path = { "/findAllPotencialesFamilias" })
      public List<PotencialFamilia> findAllPotencialesFamilias() {
            return this.repository.findAllPotencialesFamilias();
      }

}
