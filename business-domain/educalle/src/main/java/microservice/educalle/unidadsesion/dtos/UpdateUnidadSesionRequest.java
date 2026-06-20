package microservice.educalle.unidadsesion.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUnidadSesionRequest {

   @NotNull
   private Integer idSesion;

   @NotBlank
   private String nombre;

   @NotBlank
   private String descripcion;

   @NotNull
   private Integer usuRegistra;

}
