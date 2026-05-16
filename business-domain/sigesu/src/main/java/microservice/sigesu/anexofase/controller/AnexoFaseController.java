package microservice.sigesu.anexofase.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import microservice.sigesu.anexofase.model.AnexoFase;
import microservice.sigesu.anexofase.service.AnexoFaseService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class AnexoFaseController {

   private final AnexoFaseService service;

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
