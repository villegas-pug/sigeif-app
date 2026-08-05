package microservice.punche.unidadfuncionalusuario.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.punche.unidadfuncionalusuario.dtos.UnidadFuncionalUsuarioQuery;
import microservice.punche.unidadfuncionalusuario.model.UnidadFuncionalUsuario;
import microservice.punche.unidadfuncionalusuario.service.UnidadFuncionalUsuarioService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

@RestController
@AllArgsConstructor
public class UnidadFuncionalUsuarioController {

   private final UnidadFuncionalUsuarioService service;

   @GetMapping(path = { "/findUnidadFuncionalUsuarioByIdUsuario" })
   public ResponseEntity<ApiResponse<List<UnidadFuncionalUsuario>>> findUnidadFuncionalUsuarioByIdUsuario(
         @RequestParam Long idUsuario) {
      return ResponseEntity.ok(
            ApiResponse.<List<UnidadFuncionalUsuario>>builder()
                  .message(ApiResponseStatus.SUCCESS.getMessage())
                  .data(this.service.findUnidadFuncionalUsuarioByIdUsuario(
                        UnidadFuncionalUsuarioQuery.builder().idUsuario(idUsuario).build()))
                  .build());
   }
}
