package microservice.sigesu.equipotrabajo.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.sigesu.personal.model.Personal;
import microservice.sigesu.zona.model.ZonaIntervencion;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "idEquipo")
public class EquipoTrabajo {

   private Long idEquipo;
   private ZonaIntervencion zonaIntervencion;
   private Personal personal;
   private Cargo cargo;
   private Long usuRegistra;
   private LocalDate fecRegistra;
   private Long usuActualiza;
   private LocalDate fecActualiza;
   private Integer estado;
   private Integer eliminado;

}
