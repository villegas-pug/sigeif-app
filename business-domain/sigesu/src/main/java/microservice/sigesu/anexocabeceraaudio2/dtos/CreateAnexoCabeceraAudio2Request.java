package microservice.sigesu.anexocabeceraaudio2.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateAnexoCabeceraAudio2Request {

	@NotNull
	private Long idAnexoCabecera;

	private String nombreArchivo;

	private Integer estado;

}
