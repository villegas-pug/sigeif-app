package microservice.cedif.infrastructure.adapters.in.web.controllers.unidadorganica;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.ports.in.unidadorganica.UnidadOrganicaServicePort;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class UnidadOrganicaController {

   private final UnidadOrganicaServicePort service;
   private final UnidadOrganicaResponseMapper responseMapper;

   @GetMapping(path = { "/findUnidadesOrganicasByNombreReferencia" })
   public ResponseEntity<ApiResponse<List<UnidadOrganicaResponse>>> findUnidadesOrganicasByNombreReferencia(
         @RequestParam String ref) {

      List<UnidadOrganicaResponse> unidadesOrganicasResponse = service.findUnidadesOrganicasByNombreReferencia(ref)
            .stream().map(this.responseMapper::toResponse).toList();
      return ResponseEntity.ok(
            ApiResponse
                  .<List<UnidadOrganicaResponse>>builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(unidadesOrganicasResponse)
                  .build());
   }

}
