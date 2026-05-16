package microservice.sigesu.anexopregunta.dtos;

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
   private Integer obligatoria;
   private String pregunta2;
   private String tipoControl2;
   private String opciones2;
   private Integer obligatoria2;
   private String tipoDato1;
   private String tipoDato2;
   private String condicion;
}