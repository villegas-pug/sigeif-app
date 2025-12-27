package microservice.cedif.domain.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotivoReferecia {

   private Integer idMotivo;
   private String descripcion;
   private LocalDate fechaRegistra;
   private Integer estado;
   private Integer eliminado;

}
