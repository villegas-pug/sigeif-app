package microservice.cedif.domain.ports.in.centroreferencia;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CentroReferenciaCreateCommand {

   private String nombre;
   private String representante;
   private String ruc;
   private String contacto;
   private String telefono;
   private String direccion;
   private String referencia;
   private String correo;
   private Integer usuRegistra;
   private String ubigeo;

}
