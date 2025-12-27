package microservice.cedif.domain.ports.in.potencialfamilia;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.models.MotivoReferecia;
import microservice.cedif.domain.ports.in.anexorespuesta.AnexoRespuestaCreateCommand;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PotencialFamiliaCreateCommand {

   private String codFamilia;
   private Integer idUnidadOrganica;
   private LocalDate fecRegistra;
   private Integer usuRegistra;

   private List<MotivoReferecia> motivosReferencia;
   private List<FamiliaIntegrante> integrantes;
   private List<AnexoRespuestaCreateCommand> anexosRespuestas;

}
