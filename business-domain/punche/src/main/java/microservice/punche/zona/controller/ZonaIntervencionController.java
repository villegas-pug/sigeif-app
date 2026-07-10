package microservice.punche.zona.controller;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.punche.zona.dtos.UpdateZonaIntervencionRequest;
import microservice.punche.zona.dtos.ZonaIntervencionPaginatedResponse;
import microservice.punche.zona.dtos.ZonaIntervencionResponse;
import microservice.punche.zona.dtos.ZonaIntervencionSaveDto;
import microservice.punche.zona.mappers.ZonaIntervencionUpdateMapper;
import microservice.punche.zona.model.ZonaIntervencion;
import microservice.punche.zona.service.ZonaIntervencionService;
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

      @GetMapping(path = { "/findZonasIntervencionByParamsPaginated" })
      public ResponseEntity<ApiResponse<List<ZonaIntervencion>>> findZonasIntervencionByParamsPaginated(
                  @RequestParam String descripcionZona,
                  @RequestParam int anioRegistroZona,
                  @RequestParam int mesRegistroZona,
                  @RequestParam(required = false) String codFamilia,
                  @RequestParam(defaultValue = "1") int page,
                  @RequestParam(defaultValue = "10") int rowsPerPage) {
            ZonaIntervencionPaginatedResponse response = this.service.findZonasIntervencionByParamsPaginated(
                        descripcionZona,
                        anioRegistroZona,
                        mesRegistroZona,
                        codFamilia,
                        page,
                        rowsPerPage);

            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<ZonaIntervencion>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(response.getItems())
                                    .totalRows(response.getTotalRows())
                                    .page(response.getPage())
                                    .rowsPerPage(response.getRowsPerPage())
                                    .build());
      }

      @GetMapping(path = { "/findZonasIntervencionShortByParams" })
      public ResponseEntity<ApiResponse<List<ZonaIntervencion>>> findZonasIntervencionShortByParams(
                  @RequestParam String descripcionZona,
                  @RequestParam int anioRegistroZona, @RequestParam int mesRegistroZona) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<ZonaIntervencion>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findZonasIntervencionShortByParams(descripcionZona,
                                                anioRegistroZona, mesRegistroZona))
                                    .build());
      }

      @GetMapping(path = { "/findZonasIntervencionMinifiedByParams" })
      public ResponseEntity<ApiResponse<List<ZonaIntervencion>>> findZonasIntervencionMinifiedByParams(
                  @RequestParam String descripcionZona,
                  @RequestParam int anioRegistroZona, @RequestParam int mesRegistroZona) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<ZonaIntervencion>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findZonasIntervencionMinifiedByParams(descripcionZona,
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

      @GetMapping(path = { "/findZonaIntervencionMinifiedById" })
      public ResponseEntity<?> findZonaIntervencionMinifiedById(@RequestParam Long idZona) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findZonaIntervencionMinifiedById(idZona))
                                    .build());
      }

      @GetMapping(path = { "/findZonasIntervencionToEjecSesionesByParams" })
      public ResponseEntity<ApiResponse<List<ZonaIntervencion>>> findZonasIntervencionToEjecSesionesByParams(
                  @RequestParam String descripcionZona,
                  @RequestParam int anioRegistroZona, @RequestParam int mesRegistroZona) {
            return ResponseEntity.ok(
                        ApiResponse
                                    .<List<ZonaIntervencion>>builder()
                                    .message(ApiResponseStatus.SUCCESS.getMessage())
                                    .data(this.service.findZonasIntervencionToEjecSesionesByParams(descripcionZona,
                                                anioRegistroZona, mesRegistroZona))
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
