package microservice.punche.unidadtema.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.punche.objetivoespecifico.models.Modulo;
import microservice.punche.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.punche.objetivoespecifico.models.Unidad;
import microservice.punche.taller.model.Taller;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idTema" })
public class UnidadTema {

   private Integer idTema;
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
