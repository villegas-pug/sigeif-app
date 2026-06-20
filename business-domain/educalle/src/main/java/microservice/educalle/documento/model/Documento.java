package microservice.educalle.documento.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Documento {

   private Long idTipoDoc;
   private String tipoDoc;

}
