package microservice.punche.gruposocial.controller;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.punche.gruposocial.service.GrupoSocialService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@AllArgsConstructor
public class GrupoSocialController {

   private final GrupoSocialService service;

   @GetMapping(path = { "/findAllGrupoSocial" })
   public ResponseEntity<?> findAllGrupoSocial() {
      return ResponseEntity.ok(
            ApiResponse
                  .builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(this.service.findAllGrupoSocial())
                  .build());
   }

}