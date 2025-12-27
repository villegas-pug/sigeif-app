package microservice.punche.ejecucionsesionintegrante.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.punche.ejecucionsesion.model.EjecucionSesion;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idSesionIntegrante" })
public class EjecucionSesionIntegrante {

   private Long idSesionIntegrante;
   private EjecucionSesion ejecucionSesion;
   private FamiliaIntegrante integranteFamilia;
   private Integer asistio;
   private Integer usuRegistra;
   private LocalDate fechaRegistra;
   private Integer usuarioElimina;
   private LocalDate fechaElimina;
   private Integer estado;
   private Integer eliminado;

}
