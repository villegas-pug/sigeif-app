package microservice.cedif.infrastructure.adapters.in.web.controllers.anexofase;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.AnexoFase;
import microservice.cedif.domain.ports.in.anexofase.AnexoFaseServicePort;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class AnexoFaseController {

   private final AnexoFaseServicePort service;

   @GetMapping(path = { "/findAnexoFasesByNumAnexo" })
   public ResponseEntity<ApiResponse<List<AnexoFase>>> findAnexoFasesByNumAnexo(@RequestParam Integer numAnexo) {
      return ResponseEntity.ok(
            ApiResponse
                  .<List<AnexoFase>>builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(this.service.findAnexoFasesByNumAnexo(numAnexo))
                  .build());
   }

}
