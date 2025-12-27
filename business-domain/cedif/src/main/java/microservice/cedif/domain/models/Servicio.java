package microservice.cedif.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idServicio" })
public class Servicio {
   private Long idServicio;
   private String nombre;
}
