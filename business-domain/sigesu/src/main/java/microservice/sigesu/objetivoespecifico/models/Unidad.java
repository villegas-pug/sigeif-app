package microservice.sigesu.objetivoespecifico.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.sigesu.unidadsesion.model.UnidadSesion;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idUnidad" })
public class Unidad {

   private Integer idUnidad;
   private List<Tema> temas;
   private List<UnidadSesion> sesiones;
   private String nombre;
   private String descripcion;
   private Integer estado;
   private Integer eliminado;

}
