package microservice.cedif.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catalogo {

   private Long idCatalogo;
   private String catDescripcion;
   private Long grupo;
   private Long subgrupo;
   private String catAbreviatura;

}
