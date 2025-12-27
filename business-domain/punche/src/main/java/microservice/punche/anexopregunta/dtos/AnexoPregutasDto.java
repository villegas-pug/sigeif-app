package microservice.punche.anexopregunta.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnexoPregutasDto {

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

}