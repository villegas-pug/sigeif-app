package microservice.sigesu.zona.dtos;

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
public class UpdateZonaIntervencionRequest {

   @NotNull
   private Long idZona;

   @NotBlank
   private String descripcion;

   private Integer idInstitucion;
   private Integer idUnidadorg;

   @NotNull
   private Integer codTipo;

   @NotNull
   private Integer usuActualiza;

}
