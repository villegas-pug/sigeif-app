package microservice.cedif.domain.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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

   private Long idZona;
   private Long idAliado;
   private Servicio servicio;
   private Personal acompañante;

   private Set<AnexoRespuesta> anexosRespuestas;
   private Set<FamiliaIntegrante> integrantesFamilia;
   private UnidadOrganica unidadOrganica;

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
