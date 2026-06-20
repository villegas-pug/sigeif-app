package microservice.educalle.anexorespuesta.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnexoEvaluacionResponse {

    private Long idCabecera;
    private Integer correlativo;
    private Integer totalRespuestas;

}