package microservice.punche.taller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.punche.objetivoespecifico.models.Modulo;
import microservice.punche.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.punche.unidadsesion.model.UnidadSesion;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idTaller" })
public class Taller {

   private Integer idTaller;
   private ObjetivoEspecifico objetivoEspecifico;
   private Modulo modulo;
   private UnidadSesion sesion;
   private String nombre;
   private String descripcion;
   private Integer usuRegistra;
   private Integer usuarioElimina;
   private Integer estado;
   private Integer eliminado;

}