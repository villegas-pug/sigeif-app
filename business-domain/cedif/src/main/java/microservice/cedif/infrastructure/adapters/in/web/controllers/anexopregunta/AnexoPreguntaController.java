package microservice.cedif.infrastructure.adapters.in.web.controllers.anexopregunta;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.AnexoPregunta;
import microservice.cedif.domain.ports.in.anexopregunta.AnexoPreguntaServicePort;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class AnexoPreguntaController {

      private final AnexoPreguntaServicePort service;

      @GetMapping(path = { "/findAllAnexoPregustasByParams" })
      public ResponseEntity<?> findAllAnexoPregustasByParams(@RequestParam Integer idServicio,
                  @RequestParam Integer anexo,
                  @RequestParam(required = false) Integer grupo) {
            return ResponseEntity.ok(
                        ApiResponse.builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findAllAnexoPregustasByParams(idServicio, anexo, grupo))
                                    .build());
      }

      @GetMapping(path = { "/findAllAnexoPregustasOfIntegranteByParams" })
      public ResponseEntity<ApiResponse<List<AnexoPregunta>>> findAllAnexoPregustasOfIntegranteByParams(
                  @RequestParam Integer anexo,
                  @RequestParam Long idIntegrante) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<AnexoPregunta>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findAllAnexoPregustasOfIntegranteByParams(anexo,
                                                idIntegrante))
                                    .build());
      }

}
