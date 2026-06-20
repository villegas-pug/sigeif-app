package microservice.educalle.potencialfamilia.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.educalle.aliado.model.Aliado;
import microservice.educalle.anexorespuesta.model.AnexoRespuesta;
import microservice.educalle.codigofamilia.model.CodigoFamilia;
import microservice.educalle.familiaintegrante.model.FamiliaIntegrante;
import microservice.educalle.motivoreferecia.model.MotivoReferecia;
import microservice.educalle.personal.model.Personal;
import microservice.educalle.servicio.model.Servicio;
import microservice.educalle.zona.model.ZonaIntervencion;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idFamilia" })
public class PotencialFamilia {

   private Long idFamilia;
   private String codFamilia;

   private List<CodigoFamilia> codigoFamilia;

   private ZonaIntervencion zonaIntervencion;
   private Aliado aliado;
   private Servicio servicio;
   private Personal acompañante;

   private Set<AnexoRespuesta> anexosRespuestas;
   private Set<FamiliaIntegrante> integrantesFamilia;
   private List<MotivoReferecia> motivosReferencia;

   private String observaciones;
   private Integer familiaApta;
   private Integer usuRegistra;
   private LocalDate fecRegistra;
   private Integer usuActualiza;
   private LocalDate fecActualiza;
   private Integer estado;
   private Integer eliminado;

   // ? Transient
   List<EstadoAnexoProjectionResponse> estadoFichas;

}
