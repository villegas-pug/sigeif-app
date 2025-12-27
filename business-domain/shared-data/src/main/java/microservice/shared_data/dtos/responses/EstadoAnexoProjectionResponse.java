package microservice.shared_data.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstadoAnexoProjectionResponse {

   private Integer numAnexo;
   private Integer numGrupo;
   private Integer idPregunta;
   private String pregunta;
   private Integer idFase;
   private Integer estado;

}
