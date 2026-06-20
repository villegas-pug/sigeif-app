package microservice.educalle.contacto.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.educalle.contacto.service.ContactoService;
import microservice.educalle.contacto.dtos.*;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class ContactoController {

   private final ContactoService service;

   @GetMapping(path = { "/findContactoById" })
   public ResponseEntity<?> findContactoById(@RequestParam Long idContacto) {
      ContactoResponseDto contacto = this.service.findContactoById(idContacto);
      return ResponseEntity.ok(
            ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).data(contacto).build());
   }

   @PostMapping(path = { "/createContacto" })
   public ResponseEntity<?> createContacto(@RequestBody @Valid ContactoCreateRequestDto contactoDto) {
      this.service.createContacto(contactoDto);
      return ResponseEntity.ok(
            ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).build());
   }

   @PutMapping(path = { "/updateContacto" })
   public ResponseEntity<?> updateContacto(@RequestBody @Valid ContactoUpdateRequestDto contactoDto) {
      this.service.updateContacto(contactoDto);
      return ResponseEntity.ok(
            ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).build());
   }

   @DeleteMapping(path = { "/deleteContactoById/{idContacto}" })
   public ResponseEntity<?> deleteContactoById(@PathVariable Long idContacto) {
      this.service.deleteContactoById(idContacto);
      return ResponseEntity.ok(
            ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).build());
   }

}