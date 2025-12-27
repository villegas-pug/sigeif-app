package microservice.punche.equipotrabajo.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateEquipoTrabajoRequest {

   @NotNull
   private Long idZona;

   @NotNull
   private Long idPersonal;

   @NotNull
   private Long idCargo;

   private String telefono;
   private String correo;

   @NotNull
   @Positive
   @Min(1)
   private Long usuRegistra;

}
