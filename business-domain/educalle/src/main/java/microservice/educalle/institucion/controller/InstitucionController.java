package microservice.educalle.institucion.controller;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.educalle.institucion.dtos.InstitucionCreateRequestDto;
import microservice.educalle.institucion.service.InstitucionService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
public class InstitucionController {

    private final InstitucionService service;

    /*
     * @PostMapping(path = { "/createInstitucion" })
     * public ResponseEntity<?> createInstitucion(@Valid @RequestBody
     * InstitucionCreateRequestDto institucionDto) {
     * this.service.createInstitucion(institucionDto);
     * return ResponseEntity.ok(
     * ApiResponse.builder().message(ApiResponseStatus.SUCCESS.getMessage()).build()
     * );
     * }
     */

}
