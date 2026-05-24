package microservice.sigesu.anexocabeceraaudio2.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateAnexoCabeceraAudio2Request {

	@NotNull
	private Long idAudio;

	@NotNull
	private Long idAnexoCabecera;

	private String nombreArchivo;

	private Integer estado;

}
