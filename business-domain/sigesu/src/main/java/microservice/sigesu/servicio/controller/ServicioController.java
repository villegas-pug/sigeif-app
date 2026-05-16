package microservice.sigesu.servicio.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.sigesu.servicio.service.ServicioService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@AllArgsConstructor
public class ServicioController {

   private final ServicioService service;

   @GetMapping(path = { "/findAllServicios" })
   public ResponseEntity<?> findAllServicios() {
      return ResponseEntity.ok(
            ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).data(this.service.findAllServicios())
                  .build());
   }

}
