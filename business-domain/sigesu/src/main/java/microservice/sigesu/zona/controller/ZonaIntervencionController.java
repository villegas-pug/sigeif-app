package microservice.sigesu.zona.controller;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.sigesu.zona.dtos.UpdateZonaIntervencionRequest;
import microservice.sigesu.zona.dtos.ZonaIntervencionParamsDto;
import microservice.sigesu.zona.dtos.ZonaIntervencionResponse;
import microservice.sigesu.zona.dtos.ZonaIntervencionSaveDto;
import microservice.sigesu.zona.mappers.ZonaIntervencionUpdateMapper;
import microservice.sigesu.zona.model.ZonaIntervencion;
import microservice.sigesu.zona.service.ZonaIntervencionService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@AllArgsConstructor
public class ZonaIntervencionController {

      private final ZonaIntervencionService service;
      private final ZonaIntervencionUpdateMapper updateMapper;

      @PostMapping(path = { "/saveZonaIntervencion" })
      public ResponseEntity<?> saveZonaIntervencion(@RequestBody @Valid ZonaIntervencionSaveDto zonaIntervencion) {
            this.service.saveZonaIntervencion(zonaIntervencion);
            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).build());
      }

      @PutMapping(path = { "/updateZonaIntervencion" })
      public ResponseEntity<?> updateZonaIntervencion(
                  @RequestBody @Valid UpdateZonaIntervencionRequest zonaIntervencion) {
            this.service.updateZonaIntervencion(this.updateMapper.toModel(zonaIntervencion));
            return ResponseEntity.ok(
                        ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).build());
      }

      @GetMapping(path = { "/findZonasIntervencionByDescripcionContaining" })
      public ResponseEntity<ApiResponse<List<ZonaIntervencionResponse>>> findZonasIntervencionByDescripcionContaining(
                  @RequestParam String descripcionZona) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<ZonaIntervencionResponse>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findZonasIntervencionByDescripcionContaining(descripcionZona))
                                    .build());
      }

      @GetMapping(path = { "/findZonasIntervencionByParams" })
      public ResponseEntity<ApiResponse<List<ZonaIntervencion>>> findZonasIntervencionByParams(
                  @RequestParam String descripcionZona,
                  @RequestParam int anioRegistroZona, @RequestParam int mesRegistroZona) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<ZonaIntervencion>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findZonasIntervencionByParams(descripcionZona,
                                                anioRegistroZona, mesRegistroZona))
                                    .build());
      }

      @GetMapping(path = { "/findZonaIntervencionById" })
      public ResponseEntity<?> findZonaIntervencionById(@RequestParam Long idZona) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findZonaIntervencionById(idZona))
                                    .build());
      }

      @DeleteMapping(path = { "/deleteZonaIntervencionById/{idZona}" })
      public ResponseEntity<?> deleteZonaIntervencionById(@PathVariable Long idZona) {
            ApiResponseStatus status = ApiResponseStatus.SUCCESS_DELETE;
            status.setMessage(idZona);
            this.service.deleteZonaIntervencionById(idZona);
            return ResponseEntity.ok(
                        ApiResponse.builder().message(status.getMessage()).build());
      }

}
