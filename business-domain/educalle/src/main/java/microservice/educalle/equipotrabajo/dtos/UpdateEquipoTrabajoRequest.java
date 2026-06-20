package microservice.educalle.equipotrabajo.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateEquipoTrabajoRequest {

   @NotNull
   private Long idEquipo;

   @NotNull
   private Long idPersonal;

   private String telefono;
   private String correo;

   @NotNull
   private Long idCargo;

   @NotNull
   @Positive
   @Min(1)
   private Long usuActualiza;

}
