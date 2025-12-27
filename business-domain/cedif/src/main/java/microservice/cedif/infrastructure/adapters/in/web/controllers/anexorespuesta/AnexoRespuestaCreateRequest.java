package microservice.cedif.infrastructure.adapters.in.web.controllers.anexorespuesta;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnexoRespuestaCreateRequest {

   private Long idFamilia;

   @NotNull
   private Long idPregunta;

   @NotNull
   private Integer usuRegistra;

   private Long idIntegrante;
   private Integer destinatario;
   private String respuesta;
   private String observacion;
   private Integer fase;
   private Integer idPersonal;
   private LocalDate fechaRegistra;

}
