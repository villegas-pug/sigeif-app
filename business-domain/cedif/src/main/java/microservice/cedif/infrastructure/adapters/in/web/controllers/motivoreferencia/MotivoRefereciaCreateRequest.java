package microservice.cedif.infrastructure.adapters.in.web.controllers.motivoreferencia;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotivoRefereciaCreateRequest {

   @NotNull
   private Integer idMotivo;
   private String descripcion;
   private LocalDate fechaRegistra;
   private Integer estado;
   private Integer eliminado;

}
