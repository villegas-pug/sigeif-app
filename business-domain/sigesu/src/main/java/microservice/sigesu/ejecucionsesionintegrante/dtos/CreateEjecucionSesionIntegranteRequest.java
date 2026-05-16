package microservice.sigesu.ejecucionsesionintegrante.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEjecucionSesionIntegranteRequest {

   private Long idSesionIntegrante;

   @NotNull
   private Long idIntegrante;

   @NotNull
   private Integer asistio;

   @NotNull
   private Integer usuRegistra;

}
