package microservice.educalle.catalogo.controller;

import org.springframework.web.bind.annotation.RestController;

import microservice.educalle.catalogo.service.CatalogoService;
import microservice.educalle.pais.service.PaisService;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CatalogoController {

	@Autowired
	private CatalogoService service;

	@Autowired
	private PaisService paisService;

	@GetMapping(path = { "/findAllCatalogosByGrupos" })
	public ResponseEntity<?> findAllCatalogosByGrupos(@RequestParam Integer grupo,
			@RequestParam(required = false) Integer subgrupo) {
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

	@GetMapping(path = { "/findAllNivelesEducativos" })
	public ResponseEntity<?> findAllNivelesEducativos(@RequestParam(required = false) Integer idTipoEdu,
			@RequestParam(required = false) Integer idNivelEdu) {
		return ResponseEntity
				.ok(
						ApiResponse
								.builder()
								.message(ApiResponseStatus.SUCCESS.getMessage())
								.data(this.service.findAllNivelesEducativos(idTipoEdu, idNivelEdu))
								.build());

	}

}
