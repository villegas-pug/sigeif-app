package microservice.cedif.infrastructure.adapters.in.web.controllers.catalogo;

import org.springframework.web.bind.annotation.RestController;

import microservice.cedif.domain.ports.in.catalogo.CatalogoServicePort;
import microservice.cedif.domain.ports.in.pais.PaisServicePort;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CatalogoController {

      @Autowired
      private CatalogoServicePort service;

      @Autowired
      private PaisServicePort paisService;

      @GetMapping(path = { "/findAllCatalogosByGrupos" })
      public ResponseEntity<?> findAllCatalogosByGrupos(@RequestParam Integer grupo, @RequestParam Integer subgrupo) {
            return ResponseEntity
                        .ok(
                                    ApiResponse
                                                .builder()
                                                .message(ApiResponseStatus.SUCCESS.getMessage())
                                                .data(this.service.findAllCatalogosByGrupos(grupo, subgrupo))
                                                .build());

      }

      @GetMapping(path = { "/findAllPais" })
      public ResponseEntity<?> findAllPais() {
            return ResponseEntity
                        .ok(
                                    ApiResponse
                                                .builder()
                                                .message(ApiResponseStatus.SUCCESS.getMessage())
                                                .data(this.paisService.findAllPais())
                                                .build());
      }

}
