package microservice.sigesu.anexorespuesta.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetIntegranteAnexoRespuestaByParamsQuery {
   private Integer idIntegrante;
   private Integer anexo;
   private Integer grupo;
}
