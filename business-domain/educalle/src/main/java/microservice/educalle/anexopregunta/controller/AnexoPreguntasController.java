package microservice.educalle.anexopregunta.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.educalle.anexopregunta.service.AnexoPreguntasService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class AnexoPreguntasController {

      private final AnexoPreguntasService service;

      @GetMapping(path = { "/findAllAnexoPregustasByParams" })
      public ResponseEntity<?> findAllAnexoPregustasByParams(@RequestParam Integer idServicio,
                  @RequestParam Integer anexo,
                  @RequestParam(required = false) Integer grupo) {
            System.out.println("CONTROLLER idServicio: " + idServicio);
            System.out.println("CONTROLLER anexo: " + anexo);
            return ResponseEntity.ok(
                        ApiResponse.builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findAllAnexoPregustasByParams(idServicio, anexo, grupo))
                                    .build());
      }

      @GetMapping("/findAllAnexoPregustasByParams2")
      public ResponseEntity<?> findAllAnexoPregustasByParams2(
                  @RequestParam Integer idServicio,
                  @RequestParam Integer anexo,
                  @RequestParam(required = false) Integer grupo) {

            return ResponseEntity.ok(
                        ApiResponse.builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(service.findAllAnexoPregustasByParams2(idServicio, anexo, grupo))
                                    .build());
      }

}
