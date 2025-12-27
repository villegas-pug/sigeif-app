package microservice.cedif.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pais {

   private Long idPais;
   private String nombre;
   private String nacionalidad;

}
