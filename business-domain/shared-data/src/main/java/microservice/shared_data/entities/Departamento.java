package microservice.shared_data.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {

   private String idUbigeo;
   private String idDepartamento;
   private String nombre;

}