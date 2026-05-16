package microservice.sigesu.anexopregunta.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnexoPregunta {

   private Long idPregunta;
   private Integer idServicio;
   private Integer numAnexo;
   private Integer numGrupo;
   private Integer numPregunta;
   private String pregunta;
   private String opciones;
   private String tipoControl;
   private Integer condicionSi;
   private Integer condicionNo;
   private String pregunta2;
   private String opciones2;
   private String tipoControl2;
}
