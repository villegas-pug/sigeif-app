package microservice.cedif.infrastructure.adapters.in.web.controllers.familiaintegrante;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.ports.in.integrantefamilia.IntegranteFamiliaServicePort;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@AllArgsConstructor
@Validated
public class FamiliaIntegranteController {

      private final IntegranteFamiliaServicePort service;
      private final FamiliaIntegranteModelMapper mapper;

      @PostMapping(path = { "/createIntegranteFamilia" })
      public ResponseEntity<?> createIntegranteFamilia(
                  @RequestBody @Valid FamiliaIntegranteCreateRequest integranteFamilia) {
            FamiliaIntegrante model = this.service
                        .createIntegranteFamilia(this.mapper.fromCreateToModel(integranteFamilia));
            return ResponseEntity.ok(
                        ApiResponse.builder().data(model).message(ApiResponseStatus.SUCCESS.getMessage()).build());
      }

      @PutMapping(path = { "/updateIntegranteFamilia" })
      public ResponseEntity<?> updateIntegranteFamilia(
                  @RequestBody @Valid FamiliaIntegranteUpdateRequest integranteFamilia) {
            FamiliaIntegrante model = this.service
                        .updateIntegranteFamilia(this.mapper.fromUpdateToModel(integranteFamilia));
            return ResponseEntity.ok(
                        ApiResponse.builder().data(model).message(ApiResponseStatus.SUCCESS.getMessage()).build());
      }

      @GetMapping(path = { "/findFamiliaIntegranteById" })
      public ResponseEntity<?> findFamiliaIntegranteById(@RequestParam Long idIntegrante) {
            FamiliaIntegrante model = this.service.findFamiliaIntegranteById(idIntegrante);
            return ResponseEntity.ok(
                        ApiResponse.builder().data(model).message(ApiResponseStatus.SUCCESS.getMessage()).build());
      }

      @PutMapping(path = { "/updateIntegrantesFamilia" })
      public ResponseEntity<?> updateIntegrantesFamilia(
                  @RequestBody @Valid List<@Valid FamiliaIntegranteUpdateRequest> familiaIntegrantes) {
            List<FamiliaIntegrante> models = this.service
                        .updateIntegrantesFamilia(this.mapper.fromUpdatesToModels(familiaIntegrantes));
            return ResponseEntity
                        .ok(ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).data(models).build());

      }

      @DeleteMapping(path = { "/deleteFamiliaIntegranteById" })
      public ResponseEntity<?> deleteFamiliaIntegranteById(@RequestParam Long idIntegrante) {
            var modelDeleted = this.service.deleteFamiliaIntegranteById(idIntegrante);
            ApiResponseStatus apiStatus = ApiResponseStatus.SUCCESS_DELETE;
            apiStatus.setMessage(idIntegrante);

            return ResponseEntity.ok(
                        ApiResponse.builder().message(apiStatus.getMessage()).data(modelDeleted).build());
      }

}
