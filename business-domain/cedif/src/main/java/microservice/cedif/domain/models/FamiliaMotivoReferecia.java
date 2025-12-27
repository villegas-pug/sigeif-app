package microservice.cedif.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamiliaMotivoReferecia {

   private Long idFmr;
   private Long idFamilia;
   private Long idMotivo;
   private Integer estado;

}
