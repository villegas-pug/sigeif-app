package microservice.educalle.acta.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import microservice.educalle.acta.dto.ActaResponse;
import microservice.educalle.acta.mappers.ActaResponseMapper;
import microservice.educalle.acta.model.Acta;
import microservice.educalle.acta.service.ActaService;
import microservice.educalle.aliado.model.Aliado;
import microservice.educalle.catalogo.model.Catalogo;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import oracle.security.o3logon.a;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@AllArgsConstructor
public class ActaController {

   private final ActaService service;
   private final ActaResponseMapper responseMapper;

   @PostMapping(path = "/createActa")
   public ResponseEntity<ApiResponse<Void>> createActa(
         @RequestParam Long idAliado,
         @RequestParam Long idTipoActa,
         @RequestParam MultipartFile anexo,
         @RequestParam LocalDate fechaActa,
         @RequestParam Integer usuRegistra) throws IOException {

      Acta newActa = Acta.builder()
            .aliado(Aliado.builder().idAliado(idAliado).build())
            .tipoActa(Catalogo.builder().idCatalogo(idTipoActa).build())
            .anexo(anexo.getBytes())
            .anexoNombre(anexo.getOriginalFilename())
            .fechaActa(fechaActa)
            .usuRegistra(usuRegistra)
            .build();

      this.service.createActa(newActa);
      return ResponseEntity.ok(
            ApiResponse.<Void>builder().message(ApiResponseStatus.SUCCESS_CREATE.getMessage()).build());
   }

   @PutMapping(path = "/updateActa")
   public ResponseEntity<ApiResponse<Void>> updateActa(
         @RequestParam Long idActa,
         @RequestParam Long idTipoActa,
         @RequestParam MultipartFile anexo,
         @RequestParam LocalDate fechaActa) throws IOException {

      Acta newActa = Acta.builder()
            .idActa(idActa)
            .tipoActa(Catalogo.builder().idCatalogo(idTipoActa).build())
            .anexo(anexo.getBytes())
            .anexoNombre(anexo.getOriginalFilename())
            .fechaActa(fechaActa)
            .build();

      this.service.updateActa(newActa);
      return ResponseEntity.ok(
            ApiResponse.<Void>builder().message(ApiResponseStatus.SUCCESS_CREATE.getMessage()).build());
   }

   @GetMapping(path = { "/downloadActa" })
   public ResponseEntity<byte[]> downloadActa(@RequestParam Long idActa) {

      Acta acta = this.service.findActaById(idActa);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentDisposition(ContentDisposition.builder("attachment").filename(acta.getAnexoNombre()).build());

      return ResponseEntity.ok().headers(headers).body(acta.getAnexo());
   }

   @DeleteMapping(path = { "/deleteActaById" })
   public ResponseEntity<ApiResponse<Void>> deleteActaById(@RequestParam Long idActa) {
      this.service.deleteActaById(idActa);
      ApiResponseStatus apiStatus = ApiResponseStatus.SUCCESS_DELETE;
      apiStatus.setMessage(idActa);
      return ResponseEntity.ok(ApiResponse.<Void>builder().message(apiStatus.getMessage()).build());
   }

   @GetMapping(path = { "/findActasByIdAliado" })
   public ResponseEntity<ApiResponse<List<ActaResponse>>> findActasByIdAliado(@RequestParam Long idAliado) {

      List<ActaResponse> actas = this.service.findActasByIdAliado(idAliado).stream()
            .map(this.responseMapper::toResponse).toList();

      return ResponseEntity.ok(
            ApiResponse.<List<ActaResponse>>builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(actas)
                  .build());
   }

}
