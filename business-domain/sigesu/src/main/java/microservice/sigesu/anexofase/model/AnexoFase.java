package microservice.sigesu.anexofase.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnexoFase {
   private Integer idFase;
   private String nombre;
   private Integer idServicio;
   private Integer numAnexo;
   private Integer orden;
   private Integer estado;
   private Integer eliminado;
}
