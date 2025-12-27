package microservice.cedif.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idInstitucion" })
public class Institucion {

   private Long idInstitucion;
   private String nombreReferencia;
   private String direccion;
   private String referencia;
   private String telefono;
   private String correo;
   private String representante; // INSREPRESENTANTE
   private String ruc; // INSRUC
   private String contacto; // INSCONTACTO
   private UbigeoNombre ubigeo; // INSCONTACTO

}
