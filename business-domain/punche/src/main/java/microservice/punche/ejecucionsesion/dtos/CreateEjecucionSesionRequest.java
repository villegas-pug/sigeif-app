package microservice.punche.ejecucionsesion.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.ejecucionsesionintegrante.dtos.CreateEjecucionSesionIntegranteRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEjecucionSesionRequest {

   @NotNull
   private Long idDetPatfam;

   @NotNull
   private Integer idSesion;

   @NotNull
   private Integer idPersonal;

   @Valid
   @NotEmpty
   private List<CreateEjecucionSesionIntegranteRequest> integrantes;

   @NotNull
   private Integer idModalidad;

   @NotNull
   private LocalDateTime fecHoraIni;

   @NotNull
   private LocalDateTime fecHoraFin;

   // ? Opcionales
   private Integer integrantesPresentes;
   private String integrantesAusentes;
   private Integer parejaPreparadaSesion;
   private String lugarEspacio;
   private String motivoFueraCasa;
   private Integer miembrosAseados;
   private Integer espacioOrdenado;
   private Integer espacioLimpio;
   private String actividadesSonRealizadas;
   private LocalDateTime fecHoraSiguienteSesion;
   private String compromiso;
   private String observaciones;

   @NotNull
   private Integer realizoSesion;

   @NotNull
   private Integer usuRegistra;

}
