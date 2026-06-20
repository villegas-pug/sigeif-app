package microservice.educalle.anexo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import microservice.educalle.anexo.service.AnexoService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

@RestController
@RequestMapping("/anexo")
@AllArgsConstructor
public class AnexoController {

        private final AnexoService anexoService;

        @GetMapping("/listar")
        public ResponseEntity<?> listarAnexos(
                        @RequestParam(required = false) Long idUnidadOrganica,
                        @RequestParam(required = false) Long idServicio,
                        @RequestParam(required = false) Long idAnexo) {

                return ResponseEntity.ok(
                                ApiResponse.builder()
                                                .message(ApiResponseStatus.SUCCESS.getMessage())
                                                .data(
                                                                anexoService.listarAnexosPorFiltro(
                                                                                idUnidadOrganica,
                                                                                idServicio,
                                                                                idAnexo))
                                                .build());

        }

        @GetMapping("/unidadesSugesu")
        public ResponseEntity<?> listarUnidades() {

                return ResponseEntity.ok(
                                ApiResponse.builder()
                                                .message(ApiResponseStatus.SUCCESS.getMessage())
                                                .data(anexoService.listarUnidades())
                                                .build());
        }

        @GetMapping("/unidades-serviciosSugesu")
        public ResponseEntity<?> listarUnidadesServicios(
                        @RequestParam Long idUnidadOrganica) {

                return ResponseEntity.ok(
                                ApiResponse.builder()
                                                .message(ApiResponseStatus.SUCCESS.getMessage())
                                                .data(anexoService.listarUnidadesServicios(idUnidadOrganica))
                                                .build());
        }

        @GetMapping("/anexos-por-servicioSugesu")
        public ResponseEntity<?> listarAnexosPorServicio(
                        @RequestParam Long idUnidadOrganica,
                        @RequestParam Long idServicio) {

                return ResponseEntity.ok(
                                ApiResponse.builder()
                                                .message(ApiResponseStatus.SUCCESS.getMessage())
                                                .data(anexoService.listarAnexosPorServicio(idUnidadOrganica,
                                                                idServicio))
                                                .build());
        }

}
