package microservice.cedif.infrastructure.adapters.in.web.controllers.unidadorganica;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.cedif.domain.models.Personal;
import microservice.cedif.domain.models.UbigeoNombre;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UnidadOrganicaResponse {

   private Long idUO;
   private String nombreReferencia;
   private String telefono;
   private String direccion;
   private String referencia;
   private String correo;
   private UbigeoNombre ubigeo; // INSCONTACTO
   private Personal representante;

}
