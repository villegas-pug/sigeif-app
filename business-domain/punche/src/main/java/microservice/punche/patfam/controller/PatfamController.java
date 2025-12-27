package microservice.punche.patfam.controller;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.punche.patfam.dtos.CreatePatfamRequest;
import microservice.punche.patfam.dtos.UpdatePatfamRequest;
import microservice.punche.patfam.mappers.PatfamCreateMapper;
import microservice.punche.patfam.mappers.PatfamUpdateMapper;
import microservice.punche.patfam.models.Patfam;
import microservice.punche.patfam.service.PatfamService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class PatfamController {

      private final PatfamService service;
      private final PatfamCreateMapper createMapper;
      private final PatfamUpdateMapper updateMapper;

      @PostMapping(path = { "/createPatfam" })
      public ResponseEntity<ApiResponse<Patfam>> createPatfam(@RequestBody @Valid CreatePatfamRequest patfamRequest) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<Patfam>builder()
                                    .message(ApiResponseStatus.SUCCESS_CREATE.getMessage())
                                    .data(this.service.createPatfam(this.createMapper.toModel(patfamRequest)))
                                    .build());
      }

      @PutMapping(path = { "/updatePatfam" })
      public ResponseEntity<ApiResponse<Patfam>> updatePatfam(@RequestBody @Valid UpdatePatfamRequest patfamRequest) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<Patfam>builder()
                                    .message(ApiResponseStatus.SUCCESS_UPDATE.getMessage())
                                    .data(this.service.updatePatfam(this.updateMapper.toModel(patfamRequest)))
                                    .build());
      }

      @GetMapping(path = { "/findPatfamByIdFamilia" })
      public ResponseEntity<ApiResponse<Patfam>> findPatfamByIdFamilia(@RequestParam @Valid Long idFamilia) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<Patfam>builder()
                                    .data(this.service.findPatfamByIdFamilia(idFamilia))
                                    .build());
      }

      @DeleteMapping(path = { "/deletePatfamById" })
      public ResponseEntity<ApiResponse<Void>> deletePatfamById(@RequestParam @Valid Long idPatfam) {
            this.service.deletePatfamById(idPatfam);

            ApiResponseStatus responseStatus = ApiResponseStatus.SUCCESS_DELETE;
            responseStatus.setMessage(idPatfam);

            return ResponseEntity.ok(
                        ApiResponse
                                    .<Void>builder()
                                    .message(responseStatus.getMessage())
                                    .build());
      }

}
