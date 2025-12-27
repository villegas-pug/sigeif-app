package microservice.punche.zona.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZonaIntervencionParamsDto {

   private Integer anioRegistroZona;
   private Integer mesRegistroZona;

   @NotNull
   private String descripcionZona;

}
