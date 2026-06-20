package microservice.educalle.catalogo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoDto {

   private Long idCatalogo;
   private Long grupo;
   private Long subgrupo;
   private String catDescripcion;
   private String catAbreviatura;

}