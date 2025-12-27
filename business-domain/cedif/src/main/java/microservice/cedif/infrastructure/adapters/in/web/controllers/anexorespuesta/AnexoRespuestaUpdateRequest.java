package microservice.cedif.infrastructure.adapters.in.web.controllers.anexorespuesta;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class AnexoRespuestaUpdateRequest {

   @NotNull
   private Long idRespuesta;

   @NotNull
   private Integer usuModifica;

   private String respuesta;
   private String observacion;
   private Integer idPersonal;
   private LocalDate fechaRegistra;

}
