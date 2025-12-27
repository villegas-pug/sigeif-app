package microservice.punche.patfam.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDetPatfamRequest {

   private Long idDetPatfam;

   @NotNull
   private Integer idObjetivo;

   private Integer idModulo;

   private Integer idUnidad;

   private Integer idTema;

   // ? Si sesion no existe, debe enviar el nombres de la sesión
   private Integer idSesion;
   private String nombreSesion;

   private Integer idTaller;

   private Integer usuRegistra;

}
