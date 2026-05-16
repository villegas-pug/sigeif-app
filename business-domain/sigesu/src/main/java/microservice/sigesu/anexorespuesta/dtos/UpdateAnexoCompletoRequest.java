package microservice.sigesu.anexorespuesta.dtos;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateAnexoCompletoRequest {
    private Long idCabecera; // ID de la cabecera del anexo a actualizar
    private Long idAnexo; // ID del anexo
    private Long idCentro; // Centro donde se aplica
    private LocalDate fechaAplicacion; // Fecha de aplicación
    private LocalDate fechaRegistro;
    private Integer usuModifica; // Usuario que modifica
    private List<RespuestaDTO> respuestas; // Lista de respuestas
    private Long idRespSupervision;
    private Long idDirector;
    private Long idSupervisado;
}