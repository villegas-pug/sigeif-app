package microservice.sigesu.programaciontallerfamilia.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idProgTallFam" })
public class CreateProgramacionTallerFamiliaRequest {

   private Long idProgTallFam;

   @NotNull
   private Long idFamilia;

   // ? Opcionales
   private Integer asistio; // TODO: Esta propiedad deberia tener `EjecucionSesionIntegrante`

   @NotNull
   private Integer usuRegistra;

   // private Integer usuarioElimina;
   // private LocalDate fechaRegistra;
   // private LocalDate fechaElimina;

}