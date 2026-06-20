package microservice.educalle.equipotrabajo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cargo {

   private Long idCargo;
   private String nombre;

}
