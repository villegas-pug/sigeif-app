package microservice.punche.taller.controller;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.punche.taller.dtos.CreateTallerRequest;
import microservice.punche.taller.dtos.UpdateTallerRequest;
import microservice.punche.taller.mappers.TallerCreateMapper;
import microservice.punche.taller.mappers.TallerUpdateMapper;
import microservice.punche.taller.model.Taller;
import microservice.punche.taller.service.TallerService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
@Valid
public class TallerController {

   private final TallerService service;
   private final TallerCreateMapper createMapper;
   private final TallerUpdateMapper updateMapper;

   @PostMapping(path = { "/createTaller" })
   public ResponseEntity<ApiResponse<Taller>> createTaller(@RequestBody @Valid CreateTallerRequest tallerRequest) {
      var newTaller = this.service.createTaller(this.createMapper.toModel(tallerRequest));
      return ResponseEntity.ok(
            ApiResponse.<Taller>builder().message(ApiResponseStatus.SUCCESS.getMessage()).data(newTaller).build());
   }

   @PutMapping(path = { "/updateTaller" })
   public ResponseEntity<ApiResponse<Taller>> updateTaller(@RequestBody @Valid UpdateTallerRequest tallerRequest) {
      var updatedTaller = this.service.updateTaller(this.updateMapper.toModel(tallerRequest));
      return ResponseEntity.ok(
            ApiResponse.<Taller>builder().message(ApiResponseStatus.SUCCESS.getMessage()).data(updatedTaller).build());
   }

   @GetMapping(path = { "/findAllTallerByIdSesion" })
   public ResponseEntity<ApiResponse<List<Taller>>> findAllTallerByIdSesion(@RequestParam Integer idSesion) {
      List<Taller> talleres = this.service.findAllTallerByIdSesion(idSesion);
      return ResponseEntity.ok(
            ApiResponse.<List<Taller>>builder().message(ApiResponseStatus.SUCCESS.getMessage()).data(talleres).build());
   }

   @GetMapping(path = { "/findAllTallers" })
   public ResponseEntity<ApiResponse<List<Taller>>> findAllTallers() {
      List<Taller> talleres = this.service.findAllTallers();
      return ResponseEntity.ok(
            ApiResponse.<List<Taller>>builder().message(ApiResponseStatus.SUCCESS.getMessage()).data(talleres).build());
   }

}
