package microservice.educalle.anexorespuesta.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespuestaDTO {
    private Long idPregunta;
    private String tipoControl;
    private String pregunta;
    private String respuesta;
    private String respuesta2;
    private String opciones;
}