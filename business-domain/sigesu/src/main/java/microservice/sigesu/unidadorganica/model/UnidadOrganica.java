package microservice.sigesu.unidadorganica.model;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.sigesu.personal.model.Personal;
import microservice.sigesu.potencialfamilia.model.PotencialFamilia;
import microservice.sigesu.ubigeo.model.UbigeoNombre;

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
   private UbigeoNombre ubigeo;
   private Personal representante;

}
