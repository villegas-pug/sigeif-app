package microservice.cedif.domain.models;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idUO" })
public class UnidadOrganica {

   private Long idUO;
   private Set<PotencialFamilia> potencialesFamilias;
   private String nombreReferencia;
   private String telefono;
   private String direccion;
   private String referencia;
   private String correo;
   private UbigeoNombre ubigeo; // INSCONTACTO
   private Personal representante;

}
