package microservice.educalle.objetivoespecifico.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.educalle.taller.model.Taller;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idTema" })
public class Tema {

   private Integer idTema;
   // private Unidad unidad;
   private List<Taller> talleres;
   private String nombre;
   private String descripcion;
   private Integer estado;
   private Integer eliminado;

}