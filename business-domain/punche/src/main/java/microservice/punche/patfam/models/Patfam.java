package microservice.punche.patfam.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.punche.potencialfamilia.model.PotencialFamilia;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = { "idPatfam" })
public class Patfam {

   private Long idPatfam;
   private PotencialFamilia familia;
   private List<DetPatfam> detPatfam;
   private String motivoReferencia;
   private String diagnostico;
   private String nombreCuidador;
   private String zonaIntervencion;
   private Integer usuRegistra;
   private Integer usuModifica;
   private Integer estado;
   private Integer eliminado;

}
