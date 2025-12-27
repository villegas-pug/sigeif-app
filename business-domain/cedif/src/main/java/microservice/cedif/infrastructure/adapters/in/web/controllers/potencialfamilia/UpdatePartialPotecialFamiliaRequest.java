package microservice.cedif.infrastructure.adapters.in.web.controllers.potencialfamilia;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePartialPotecialFamiliaRequest {

   @NotNull
   private Long idFamilia;

   @NotNull
   private Long idPersonal; // * Acompañante

   private String observaciones;
   private Integer familiaApta;

   private Integer estado;
   private Integer eliminado;

}
