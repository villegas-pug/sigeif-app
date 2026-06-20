package microservice.educalle.familiaintegrante.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.educalle.familiaintegrante.dtos.CreateFamiliaIntegranteRequest;
import microservice.educalle.familiaintegrante.dtos.UpdateFamiliaIntegranteRequest;
import microservice.educalle.familiaintegrante.model.FamiliaIntegrante;
import microservice.educalle.familiaintegrante.service.IntegranteFamiliaService;
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

      private final IntegranteFamiliaService service;

      @PostMapping(path = { "/createIntegranteFamilia" })
      public ResponseEntity<?> createIntegranteFamilia(
                  @RequestBody @Valid CreateFamiliaIntegranteRequest integranteFamilia) {
            FamiliaIntegrante model = this.service.createIntegranteFamilia(integranteFamilia);
            return ResponseEntity.ok(
                        ApiResponse.builder().data(model).message(ApiResponseStatus.SUCCESS.getMessage()).build());
      }

      @PutMapping(path = { "/updateIntegranteFamilia" })
      public ResponseEntity<?> updateIntegranteFamilia(
                  @RequestBody @Valid UpdateFamiliaIntegranteRequest integranteFamilia) {
            FamiliaIntegrante model = this.service
                        .updateIntegranteFamilia(integranteFamilia);
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
                  @RequestBody @Valid List<@Valid UpdateFamiliaIntegranteRequest> familiaIntegrantes) {
            List<FamiliaIntegrante> models = this.service
                        .updateIntegrantesFamilia(familiaIntegrantes);
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
