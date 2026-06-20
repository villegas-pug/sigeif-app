package microservice.educalle.objetivoespecifico.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.educalle.taller.model.Taller;
import microservice.educalle.unidadsesion.model.UnidadSesion;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idModulo" })
public class Modulo {

   private Integer idModulo;
   List<Unidad> unidades;
   private List<UnidadSesion> sesiones;
   private List<Taller> talleres;
   private String nombre;
   private String descripcion;
   private Integer estado;
   private Integer eliminado;

}
