package microservice.sigesu.objetivoespecifico.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.sigesu.objetivoespecifico.dtos.ObjetivoEspecificoResponse;
import microservice.sigesu.objetivoespecifico.service.ObjetivoEspecificoService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@Valid
public class ObjetivoEspecificoController {

   private final ObjetivoEspecificoService service;

   @GetMapping(path = { "/findAllObjetivosEspecificosByServicio" })
   public ResponseEntity<ApiResponse<List<ObjetivoEspecificoResponse>>> findAllObjetivosEspecificosByServicio(
         @RequestParam Long idServicio) {
      return ResponseEntity.ok(
            ApiResponse.<List<ObjetivoEspecificoResponse>>builder().message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(this.service.findAllObjetivosEspecificosByServicio(idServicio)).build());
   }

}
