package microservice.punche.personal.controller;

import org.springframework.web.bind.annotation.RestController;

import microservice.punche.personal.dtos.PersonalDto;
import microservice.punche.personal.service.PersonalService;
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
   private PersonalService service;

   @GetMapping(path = { "/findPersonalByDocumento" })
   public ResponseEntity<?> findPersonalByDocumento(@RequestParam String nroDoc) {
      List<PersonalDto> personal = this.service.findPersonalByDocumento(nroDoc);
      return ResponseEntity.ok(
            ApiResponse.builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(personal)
                  .build());
   }

   @GetMapping(path = { "/findPersonalByDynamicParam" })
   public ResponseEntity<?> findPersonalByDynamicParam(@RequestParam Integer tipoBusqueda,
         @RequestParam String dynamicValue) {
      List<PersonalDto> personal = this.service.findPersonalByDynamicParam(tipoBusqueda, dynamicValue);
      return ResponseEntity.ok(
            ApiResponse.builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(personal)
                  .build());
   }

}
