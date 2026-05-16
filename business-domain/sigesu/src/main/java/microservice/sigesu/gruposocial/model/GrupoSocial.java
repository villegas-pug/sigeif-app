package microservice.sigesu.gruposocial.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrupoSocial {

   private Long idGrupoSocial;
   private String descripcion;

}
