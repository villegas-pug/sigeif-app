package microservice.punche.anexorespuesta.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAnexoRespuestaRequest {

   private Long idRespuesta;

   // ! Debería ser obligatoria, pero otro dominio lo requiere opcional.
   // @NonNull
   private Long idFamilia;

   @NotNull
   private Long idPregunta;

   private Long idIntegrante;
   private Integer destinatario;

   @NotBlank
   private String respuesta;

   private String observacion;

   private Integer fase;
   private Integer idPersonal;

   private Integer usuActualiza;

   // ! Debería ser obligatoria, pero otro dominio lo requiere opcional.
   // @NonNull
   private Integer usuRegistra;

}
