package microservice.sigesu.familiamotivoreferecia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.sigesu.motivoreferecia.model.MotivoReferecia;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamiliaMotivoReferecia {

   private Long idFmr;
   private Long idFamilia;
   private MotivoReferecia motivo;
   private Integer estado;

}
