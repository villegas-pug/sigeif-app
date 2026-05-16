package microservice.sigesu.unidadsesion.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.sigesu.objetivoespecifico.models.Modulo;
import microservice.sigesu.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.sigesu.objetivoespecifico.models.Unidad;
import microservice.sigesu.taller.model.Taller;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idSesion" })
public class UnidadSesion {

   private Integer idSesion;
   // private ObjetivoEspecifico objetivoEspecifico; // ! Se eliminó de la entidad
   private List<Taller> talleres;
   private Unidad unidad;
   private Modulo modulo;
   private ObjetivoEspecifico objetivo;
   private String nombre;
   private String descripcion;
   private Integer usuRegistra;
   private Integer estado;
   private Integer eliminado;

}
