package microservice.sigesu.anexocabeceraaudio2.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnexoCabeceraAudio2 {

	private Long idAudio;
	private Long idAnexoCabecera;
	private byte[] audio;
	private String nombreArchivo;
	private LocalDate fechaRegistro;
	private Integer estado;
	private Integer eliminado;

}
