package microservice.punche.programaciontallerfamilia.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
import microservice.punche.programaciontaller.model.ProgramacionTaller;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idProgTallFam" })
public class ProgramacionTallerFamilia {

   private Long idProgTallFam;
   private ProgramacionTaller progTaller;
   private PotencialFamilia familia;
   private Integer asistio;
   private Integer estado;
   private Integer eliminado;
   private Integer usuRegistra;
   private Integer usuarioElimina;

   // private LocalDate fechaRegistra;
   // private LocalDate fechaElimina;

}