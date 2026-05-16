package microservice.sigesu.equipotrabajo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.sigesu.equipotrabajo.dtos.CreateEquipoTrabajoRequest;
import microservice.sigesu.equipotrabajo.dtos.EquipoTrabajoResponse;
import microservice.sigesu.equipotrabajo.dtos.UpdateEquipoTrabajoRequest;
import microservice.sigesu.equipotrabajo.mappers.EquipoTrabajoCreateMapper;
import microservice.sigesu.equipotrabajo.mappers.EquipoTrabajoUpdateMapper;
import microservice.sigesu.equipotrabajo.service.EquipoTrabajoService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@AllArgsConstructor
public class EquipoTrabajoController {

   private final EquipoTrabajoService service;
   private final EquipoTrabajoCreateMapper createMapper;
   private final EquipoTrabajoUpdateMapper updateMapper;

   @PostMapping(path = { "/createEquipoTrabajo" })
   public ResponseEntity<?> createEquipoTrabajo(
         @Valid @RequestBody CreateEquipoTrabajoRequest equipoTrabajoRequest) {
      this.service.createEquipoTrabajo(this.createMapper.toModel(equipoTrabajoRequest));
      return ResponseEntity.ok(
            ApiResponse.builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .build());
   }

   @PutMapping(path = { "/updateEquipoTrabajo" })
   public ResponseEntity<?> updateEquipoTrabajo(
         @Valid @RequestBody UpdateEquipoTrabajoRequest equipoTrabajoRequest) {
      this.service.updateEquipoTrabajo(this.updateMapper.toModel(equipoTrabajoRequest));
      return ResponseEntity.ok(
            ApiResponse.builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .build());
   }

   @GetMapping(path = { "/findEquipoTrabajoById" })
   public ResponseEntity<?> findEquipoTrabajoById(@RequestParam Long idEquipo) {
      return ResponseEntity.ok(
            ApiResponse.builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(this.service.findEquipoTrabajoById(idEquipo))
                  .build());
   }

   @DeleteMapping(path = { "/deleteEquipoTrabajoById/{idEquipo}" })
   public ResponseEntity<?> deleteEquipoTrabajoById(@PathVariable Long idEquipo) {
      this.service.deleteEquipoTrabajoById(idEquipo);
      return ResponseEntity.ok(
            ApiResponse.builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .build());
   }

   @GetMapping(path = { "/findAcompañantesByIdZona" })
   public ResponseEntity<ApiResponse<List<EquipoTrabajoResponse>>> findAcompañantesByIdZona(
         @RequestParam Long idZona) {
      return ResponseEntity.ok(
            ApiResponse
                  .<List<EquipoTrabajoResponse>>builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(this.service.findAcompañantesByIdZona(idZona))
                  .build());
   }
}