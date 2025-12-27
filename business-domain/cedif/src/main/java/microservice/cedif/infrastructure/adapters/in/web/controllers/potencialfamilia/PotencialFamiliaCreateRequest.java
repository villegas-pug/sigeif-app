package microservice.cedif.infrastructure.adapters.in.web.controllers.potencialfamilia;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.cedif.infrastructure.adapters.in.web.controllers.anexorespuesta.AnexoRespuestaCreateRequest;
import microservice.cedif.infrastructure.adapters.in.web.controllers.familiaintegrante.FamiliaIntegranteCreateRequest;
import microservice.cedif.infrastructure.adapters.in.web.controllers.motivoreferencia.MotivoRefereciaCreateRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PotencialFamiliaCreateRequest {

   // ! Eliminar
   // @NotBlank
   private String codFamilia;

   // @NotNull
   private Integer idUnidadOrganica;

   @NotNull
   private Integer usuRegistra;

   @NotNull
   private LocalDate fecRegistra;

   @Valid
   private List<MotivoRefereciaCreateRequest> motivosReferencia;

   // @NotEmpty
   @Valid
   private List<FamiliaIntegranteCreateRequest> integrantes;

   @Valid
   private List<AnexoRespuestaCreateRequest> anexosRespuestas;
}
