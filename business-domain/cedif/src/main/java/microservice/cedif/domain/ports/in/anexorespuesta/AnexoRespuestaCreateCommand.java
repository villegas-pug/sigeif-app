package microservice.cedif.domain.ports.in.anexorespuesta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnexoRespuestaCreateCommand {

   private Long idFamilia;
   private Long idPregunta;
   private String respuesta;
   private String observacion;
   private Integer idPersonal;
   private Integer usuRegistra;

}
