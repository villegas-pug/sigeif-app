package microservice.shared_data.dtos.projections;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReporteComparativoFasesFichaProjection {

   private Integer idFase;
   private Integer orden;
   private String oportunidad;
   private String campo;
   private String valor;
}
