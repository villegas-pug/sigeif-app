package microservice.educalle.catalogo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Catalogo {

   private Long idCatalogo;
   private String catDescripcion;
   private Long grupo;
   private Long subgrupo;
   private String catAbreviatura;

}
