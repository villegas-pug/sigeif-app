package microservice.educalle.patfam.models;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.educalle.ejecucionsesion.model.EjecucionSesion;
import microservice.educalle.objetivoespecifico.models.Modulo;
import microservice.educalle.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.educalle.objetivoespecifico.models.Tema;
import microservice.educalle.objetivoespecifico.models.Unidad;
import microservice.educalle.taller.model.Taller;
import microservice.educalle.unidadsesion.model.UnidadSesion;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idDetPatfam" })
public class DetPatfam {

   private Long idDetPatfam;
   private Patfam patfam;
   private List<EjecucionSesion> ejecucionSesiones;
   private ObjetivoEspecifico objetivo;
   private Modulo modulo;
   private Unidad unidad;
   private Tema tema;
   private UnidadSesion sesion;
   private Taller taller;

   private Integer usuRegistra;
   private Integer usuModifica;
   private Integer estado;
   private Integer eliminado;

}
