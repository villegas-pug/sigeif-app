package microservice.educalle.validaranexoreferencia.models;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idValAnexo" })
public class ValidacionAnexoCabecera {

   private Long idValAnexo;
   private Integer idAnexoCabecera;
   private Integer idPersonal;
   private LocalDate fechaRegistra;
   private Integer estado;
   private Integer eliminado;

}
