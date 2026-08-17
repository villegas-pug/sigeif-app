package microservice.punche.anexorespuesta.dtos;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class UpdateAnexoRespuestaRequest {

   @NotNull
   private Long idRespuesta;

   // @NotBlank
   private String respuesta;

   private String observacion;

   private Integer destinatario;

   private Integer fase;
   private Integer idPersonal;

   @NotNull
   private Integer usuModifica;

}
