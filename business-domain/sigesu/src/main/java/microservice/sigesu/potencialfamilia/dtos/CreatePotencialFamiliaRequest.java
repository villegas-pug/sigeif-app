package microservice.sigesu.potencialfamilia.dtos;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.sigesu.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.sigesu.familiaintegrante.dtos.CreateFamiliaIntegranteRequest;
import microservice.sigesu.motivoreferecia.dtos.CreateMotivoRefereciaRequest;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePotencialFamiliaRequest {

   // ! Eliminar
   // @NotBlank
   private String codFamilia;

   @NotNull
   private Long idZona;

   @NotNull
   private Long idAliado;

   private Long idServicio;

   @NotNull
   private Integer usuRegistra;

   @NotNull
   private LocalDate fecRegistra;

   private Integer idUnidadOrganica;

   @Valid
   @NotEmpty
   private List<CreateMotivoRefereciaRequest> motivosReferencia;

   @NotEmpty
   @Valid
   private List<CreateFamiliaIntegranteRequest> integrantesFamilia;

   private List<CreateAnexoRespuestaRequest> anexosRespuestas;

   private String observaciones;
}
