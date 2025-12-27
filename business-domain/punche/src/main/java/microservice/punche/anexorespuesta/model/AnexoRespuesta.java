package microservice.punche.anexorespuesta.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.anexopregunta.model.AnexoPregunta;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.punche.potencialfamilia.model.PotencialFamilia;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnexoRespuesta {

   private Long idRespuesta;
   private PotencialFamilia familia;
   private AnexoPregunta pregunta;
   private FamiliaIntegrante integrante;
   private Integer destinatario;
   private String respuesta;
   private byte[] archivo;
   private String observacion;
   private Integer fase;
   private Integer usuRegistra;
   private LocalDate fechaRegistra;
   private Integer usuModifica;
   private LocalDate fechaModifica;
   private Integer usuElimina;
   private LocalDate fechaElimina;
   private Integer eliminado;
   private Integer estado;

}
