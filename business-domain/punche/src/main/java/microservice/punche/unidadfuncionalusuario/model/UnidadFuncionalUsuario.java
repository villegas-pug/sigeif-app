package microservice.punche.unidadfuncionalusuario.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnidadFuncionalUsuario {

   private Long idUsuario;
   private String centroNombre;
   private Long idUnidadOrganica;
   private String uorNombre;
   private String uorAbreviatura;
   private Long uorServicioPadre;
   private Long zoIdZona;
   private String zoDescripcion;
}
