package microservice.educalle.motivoreferecia.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MotivoReferecia {

   private Integer idMotivo;
   private String descripcion;

   private Integer eliminado;
   private LocalDate fechaRegistra;
   private Integer estado;

   // ! Aux
   // private Long idFmr;

}
