package microservice.cedif.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UbigeoNombre {

   private String idUbigeo;
   private String idDepartamento;
   private String departamento;
   private String idProvincia;
   private String provincia;
   private String idDistrito;
   private String distrito;

}
