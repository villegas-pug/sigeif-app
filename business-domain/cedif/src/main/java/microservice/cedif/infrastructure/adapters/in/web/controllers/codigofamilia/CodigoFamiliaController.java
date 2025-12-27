package microservice.cedif.infrastructure.adapters.in.web.controllers.codigofamilia;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.ports.in.codigofamilia.CodigoFamiliaServicePort;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class CodigoFamiliaController {

      private final CodigoFamiliaServicePort service;

      @PostMapping(path = { "/generateCodFamilia" })
      public ResponseEntity<ApiResponse<String>> generateCodFamilia(@RequestParam Long idFamilia) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<String>builder()
                                    .message(ApiResponseStatus.SUCCESS_GENERATE_CODE.getMessage())
                                    .data(this.service.generateCodFamilia(idFamilia))
                                    .build());
      }

      @PostMapping(path = { "/generateCodIntegrante" })
      public ResponseEntity<ApiResponse<String>> generateCodIntegrante(@RequestParam Long idIntegrante) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<String>builder()
                                    .message(ApiResponseStatus.SUCCESS_GENERATE_CODE.getMessage())
                                    .data(this.service.generateCodIntegrante(idIntegrante))
                                    .build());
      }

}
