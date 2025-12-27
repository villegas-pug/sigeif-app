package microservice.punche.anexorespuesta.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAnexoRespuestaByParamsQuery {
   private Integer idFamilia;
   private Integer anexo;
   private Integer grupo;
}
