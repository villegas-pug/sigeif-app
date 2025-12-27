package microservice.cedif.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodigoFamilia {

   // private Long idCodigo;
   // private Long idFamilia;
   // private Long idIntegrante;

   private String codigo;
   private Integer tipoCodigo;

   public String getTipoCodigo() {
      return switch (this.tipoCodigo) {
         case 0 -> "TEMPORAL";
         case 1 -> "FAMILIA";
         case 2 -> "INTEGRANTE";
         default -> null;
      };
   }

}
