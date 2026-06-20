package microservice.educalle.anexocabeceraaudio2.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AnexoCabeceraAudio2Response {

	private Long idAudio;
	private Long idAnexoCabecera;
	private String nombreArchivo;
	private LocalDate fechaRegistro;
	private Integer estado;
	private Integer eliminado;

}
