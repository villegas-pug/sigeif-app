package microservice.punche.acta.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.aliado.model.Aliado;
import microservice.punche.catalogo.model.Catalogo;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Acta {

   private Long idActa;
   private Aliado aliado;
   private Catalogo tipoActa;
   private byte[] anexo;
   private String anexoNombre;
   private LocalDate fechaActa;
   private Integer usuRegistra;
   private LocalDate fechaRegistra;
   private Integer estado;
   private Integer eliminado;

}
