package microservice.cedif.infrastructure.adapters.in.web.controllers.potencialfamilia;

import java.time.LocalDate;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PotencialFamiliaUpdateRequest {

   @NotNull
   private Long idFamilia;

   // ! Eliminar
   private String codFamilia;

   // @NotNull
   private Integer idUnidadOrganica;

   // private List<FamiliaIntegranteCreateRequest> integrantes;
   // private List<AnexoRespuestaCreateRequest> anexosRespuestas;
   // private UnidadOrganica unidadOrganica;
   // private Integer estado;

   private Integer familiaApta;

   @NotNull
   private LocalDate fecRegistra;

   @NotNull
   private Integer usuActualiza;

}
