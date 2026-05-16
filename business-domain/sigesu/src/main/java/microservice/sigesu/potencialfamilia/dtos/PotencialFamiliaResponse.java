package microservice.sigesu.potencialfamilia.dtos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.sigesu.aliado.model.Aliado;
import microservice.sigesu.anexorespuesta.model.AnexoRespuesta;
import microservice.sigesu.codigofamilia.model.CodigoFamilia;
import microservice.sigesu.familiaintegrante.model.FamiliaIntegrante;
import microservice.sigesu.motivoreferecia.model.MotivoReferecia;
import microservice.sigesu.personal.model.Personal;
import microservice.sigesu.servicio.model.Servicio;
import microservice.sigesu.zona.model.ZonaIntervencion;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idFamilia" })
public class PotencialFamiliaResponse {

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

   // private ServicioEntity servicio;

}
