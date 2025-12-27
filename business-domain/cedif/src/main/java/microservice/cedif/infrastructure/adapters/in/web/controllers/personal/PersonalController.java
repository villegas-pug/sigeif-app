package microservice.cedif.infrastructure.adapters.in.web.controllers.personal;

import org.springframework.web.bind.annotation.RestController;

import microservice.cedif.domain.models.Personal;
import microservice.cedif.domain.ports.in.personal.PersonalServicePort;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class PersonalController {

   @Autowired
   private PersonalServicePort service;

   @GetMapping(path = { "/findPersonalByDocumento" })
   public ResponseEntity<?> findPersonalByDocumento(@RequestParam String nroDoc) {
      List<Personal> personal = this.service.findPersonalByDocumento(nroDoc);
      return ResponseEntity.ok(
            ApiResponse.builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(personal)
                  .build());
   }

   @GetMapping(path = { "/findPersonalByParams" })
   public ResponseEntity<?> findPersonalByParams(@RequestParam(required = false) String nroDoc,
         @RequestParam(required = false) String nombres) {
      List<Personal> personal = this.service.findPersonalByParams(nroDoc, nombres);
      return ResponseEntity.ok(
            ApiResponse.builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(personal)
                  .build());
   }

}
