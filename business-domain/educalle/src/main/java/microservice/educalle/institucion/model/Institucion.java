package microservice.educalle.institucion.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.educalle.ubigeo.model.UbigeoNombre;

@Data
@Builder
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
   private String representante;
   private String ruc;
   private String contacto;
   private UbigeoNombre ubigeo;

}
