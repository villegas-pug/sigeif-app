package microservice.punche.objetivoespecifico.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.punche.objetivoespecifico.models.Modulo;
import microservice.punche.taller.model.Taller;
import microservice.punche.unidadsesion.model.UnidadSesion;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = { "idObjetivo" })
public class ObjetivoEspecificoResponse {

   private Integer idObjetivo;
   private List<Modulo> modulos; // ! Usa: PUNCHE, CEDIF
   private List<Taller> talleres; // ! Usa: ACERCANDONOS
   private List<UnidadSesion> sesiones; // ! Usa: ACERCANDONOS
   private String nombre;
   private String descripcion;
   private Integer estado;
   private Integer eliminado;

}
