package microservice.punche.aliado.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.punche.aliado.dtos.AliadoResponse;
import microservice.punche.aliado.dtos.CreateAliadoRequest;
import microservice.punche.aliado.dtos.UpdateAliadoRequest;
import microservice.punche.aliado.mappers.AliadoCreateMapper;
import microservice.punche.aliado.mappers.AliadoResponseMapper;
import microservice.punche.aliado.mappers.AliadoUpdateMapper;
import microservice.punche.aliado.model.Aliado;
import microservice.punche.aliado.service.AliadoService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

@RestController
@AllArgsConstructor
public class AliadoController {

      private final AliadoService service;
      private final AliadoCreateMapper createMapper;
      private final AliadoUpdateMapper updateMapper;
      private final AliadoResponseMapper responseMapper;

      @PostMapping(path = { "/createAliado" })
      public ResponseEntity<?> createAliado(@Valid @RequestBody CreateAliadoRequest aliado) {
            Aliado newAliado = this.service.createAliado(this.createMapper.toModel(aliado));
            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).data(newAliado)
                                    .build());
      }

      @PutMapping(path = { "/updateAliado" })
      public ResponseEntity<?> updateAliado(@Valid @RequestBody UpdateAliadoRequest aliado) {
            Aliado updatedAliado = this.service.updateAliado(this.updateMapper.toModel(aliado));
            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).data(updatedAliado)
                                    .build());
      }

      @GetMapping(path = { "/findAliadoById" })
      public ResponseEntity<?> findAliadoById(@RequestParam Long idAliado) {
            AliadoResponse aliadoResponse = this.responseMapper.toResponse(this.service.findAliadoById(idAliado));
            return ResponseEntity.ok(
                        ApiResponse.builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(aliadoResponse)
                                    .build());
      }

      @DeleteMapping(path = { "/deleteAliadoById/{idAliado}" })
      public ResponseEntity<?> deleteAliadoById(@PathVariable Long idAliado) {
            this.service.deleteAliadoById(idAliado);
            return ResponseEntity.ok(
                        ApiResponse.builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .build());
      }

      @GetMapping(path = { "/findAliadosByIdZona" })
      public ResponseEntity<ApiResponse<List<AliadoResponse>>> findAliadosByIdZona(@RequestParam Long idZona) {
            List<AliadoResponse> aliadosResponse = this.service.findAliadosByIdZona(idZona).stream()
                        .map(this.responseMapper::toResponse).toList();
            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<AliadoResponse>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(aliadosResponse)
                                    .build());
      }

}
