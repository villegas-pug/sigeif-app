package microservice.cedif.domain.ports.in.anexorespuesta;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetIntegranteAnexoRespuestaByParamsQuery {
   private Integer idIntegrante;
   private Integer anexo;
   private Integer grupo;
}
