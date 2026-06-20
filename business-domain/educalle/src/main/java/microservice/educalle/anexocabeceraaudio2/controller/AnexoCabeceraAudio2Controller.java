package microservice.educalle.anexocabeceraaudio2.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.educalle.anexocabeceraaudio2.dtos.AnexoCabeceraAudio2Response;
import microservice.educalle.anexocabeceraaudio2.mappers.AnexoCabeceraAudio2ResponseMapper;
import microservice.educalle.anexocabeceraaudio2.model.AnexoCabeceraAudio2;
import microservice.educalle.anexocabeceraaudio2.service.AnexoCabeceraAudio2ReportingService;
import microservice.educalle.anexocabeceraaudio2.service.AnexoCabeceraAudio2Service;
import microservice.shared_data.controller.BaseRestController;
import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;

@RestController
@AllArgsConstructor
@Validated
public class AnexoCabeceraAudio2Controller extends BaseRestController {

	private final AnexoCabeceraAudio2Service service;
	private final AnexoCabeceraAudio2ResponseMapper responseMapper;
	private final AnexoCabeceraAudio2ReportingService reportingService;

	@PostMapping(path = { "/createAnexoCabeceraAudio2" })
	public ResponseEntity<ApiResponse<Void>> createAnexoCabeceraAudio2(
			@RequestParam("audio") MultipartFile audio,
			@RequestParam("idAnexoCabecera") Long idAnexoCabecera) throws IOException {

		this.service.guardarAudio(audio.getBytes(), audio.getOriginalFilename(), idAnexoCabecera);

		return ResponseEntity.ok(
				ApiResponse.<Void>builder().message(ApiResponseStatus.SUCCESS_CREATE.getMessage()).build());
	}

	@PutMapping(path = { "/updateAnexoCabeceraAudio2" })
	public ResponseEntity<ApiResponse<Void>> updateAnexoCabeceraAudio2(
			@RequestParam("idAudio") Long idAudio,
			@RequestParam("audio") MultipartFile audio,
			@RequestParam("estado") Integer estado) throws IOException {

		this.service.actualizarAudio(idAudio, audio.getBytes(), audio.getOriginalFilename(), estado);

		return ResponseEntity.ok(
				ApiResponse.<Void>builder().message(ApiResponseStatus.SUCCESS.getMessage()).build());
	}

	@GetMapping(path = { "/findAnexoCabeceraAudio2ById" })
	public ResponseEntity<ApiResponse<AnexoCabeceraAudio2Response>> findAnexoCabeceraAudio2ById(
			@RequestParam Long idAudio) {
		AnexoCabeceraAudio2 model = this.service.findAnexoCabeceraAudio2ById(idAudio);
		AnexoCabeceraAudio2Response response = this.responseMapper.toResponse(model);
		return ResponseEntity.ok(
				ApiResponse.<AnexoCabeceraAudio2Response>builder()
						.message(ApiResponseStatus.SUCCESS.getMessage())
						.data(response)
						.build());
	}

	@GetMapping(path = { "/findAllAnexoCabeceraAudio2" })
	public ResponseEntity<ApiResponse<List<AnexoCabeceraAudio2Response>>> findAllAnexoCabeceraAudio2() {
		List<AnexoCabeceraAudio2> models = this.service.findAllAnexoCabeceraAudio2();
		List<AnexoCabeceraAudio2Response> responses = this.responseMapper.toResponses(models);
		return ResponseEntity.ok(
				ApiResponse.<List<AnexoCabeceraAudio2Response>>builder()
						.message(ApiResponseStatus.SUCCESS.getMessage())
						.data(responses)
						.build());
	}

	@DeleteMapping(path = { "/deleteAnexoCabeceraAudio2ById" })
	public ResponseEntity<ApiResponse<Void>> deleteAnexoCabeceraAudio2ById(@RequestParam Long idAudio) {
		this.service.deleteAnexoCabeceraAudio2ById(idAudio);
		ApiResponseStatus apiStatus = ApiResponseStatus.SUCCESS_DELETE;
		apiStatus.setMessage(idAudio);
		return ResponseEntity.ok(
				ApiResponse.<Void>builder().message(apiStatus.getMessage()).build());
	}

	@GetMapping(path = { "/downloadAnexoCabeceraAudio2" })
	public ResponseEntity<byte[]> downloadAnexoCabeceraAudio2(@RequestParam Long idAudio) {
		AnexoCabeceraAudio2 model = this.service.obtenerAudioParaDescarga(idAudio);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(
				ContentDisposition.builder("attachment").filename(model.getNombreArchivo()).build());

		return ResponseEntity.ok().headers(headers).body(model.getAudio());
	}

	@GetMapping(path = { "/codigos-familias/reporte" })
	public ResponseEntity<byte[]> downloadCodigosFamiliasReporte(
			@RequestParam(required = false) Long idServicio,
			@RequestParam(required = false) Long idFamilia,
			@RequestParam(required = false) String codigo) {
		return super.buildDownloadResponseEntity("Reporte_Codigos_Familias.xlsx",
				this.reportingService.generateCodigosFamiliasExcelReport(idServicio, idFamilia, codigo));
	}

}
