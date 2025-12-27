package microservice.punche.potencialfamilia.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.punche.aliado.model.Aliado;
import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.punche.codigofamilia.model.CodigoFamilia;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.punche.motivoreferecia.model.MotivoReferecia;
import microservice.punche.personal.model.Personal;
import microservice.punche.servicio.model.Servicio;
import microservice.punche.zona.model.ZonaIntervencion;
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
