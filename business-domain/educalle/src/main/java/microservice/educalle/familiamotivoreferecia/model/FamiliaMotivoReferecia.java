package microservice.educalle.familiamotivoreferecia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.educalle.motivoreferecia.model.MotivoReferecia;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamiliaMotivoReferecia {

   private Long idFmr;
   private Long idFamilia;
   private MotivoReferecia motivo;
   private Integer estado;

}
