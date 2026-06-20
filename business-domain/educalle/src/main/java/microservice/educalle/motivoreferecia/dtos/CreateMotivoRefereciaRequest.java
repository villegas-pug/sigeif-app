package microservice.educalle.motivoreferecia.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateMotivoRefereciaRequest {

   @NotNull
   private Integer idMotivo;
   private String descripcion;
   private LocalDate fechaRegistra;
   private Integer estado;
   private Integer eliminado;

}
