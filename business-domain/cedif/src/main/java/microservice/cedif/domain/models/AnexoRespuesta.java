package microservice.cedif.domain.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnexoRespuesta {

   private Long idRespuesta;
   private PotencialFamilia familia;
   private AnexoPregunta pregunta;
   private FamiliaIntegrante integrante;
   private Integer destinatario;
   private String respuesta;
   private byte[] archivo;
   private String observacion;
   private Integer fase;
   private Personal personal;
   private Integer usuRegistra;
   private LocalDate fechaRegistra;
   private Integer usuModifica;
   private LocalDate fechaModifica;
   private Integer usuElimina;
   private LocalDate fechaElimina;
   private Integer eliminado;
   private Integer estado;

}
