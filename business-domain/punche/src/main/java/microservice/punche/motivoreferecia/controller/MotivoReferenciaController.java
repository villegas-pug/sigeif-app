package microservice.punche.motivoreferecia.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import microservice.punche.motivoreferecia.service.MotivoReferenciaService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@AllArgsConstructor
public class MotivoReferenciaController {

   private MotivoReferenciaService service;

   @GetMapping(path = "/findAllMotivosReferencia")
   public ResponseEntity<?> findAllMotivosReferencia() {
      return ResponseEntity.ok(
            ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(this.service.findAllMotivosReferencia()).build());
   }

}
