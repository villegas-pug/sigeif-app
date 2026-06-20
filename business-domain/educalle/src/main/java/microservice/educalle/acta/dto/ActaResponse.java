package microservice.educalle.acta.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.educalle.aliado.model.Aliado;
import microservice.educalle.catalogo.model.Catalogo;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActaResponse {

   private Long idActa;
   private Aliado aliado;
   private Catalogo tipoActa;
   private String anexoNombre;
   private LocalDate fechaActa;
   private Integer estado;
   private Integer eliminado;

}
