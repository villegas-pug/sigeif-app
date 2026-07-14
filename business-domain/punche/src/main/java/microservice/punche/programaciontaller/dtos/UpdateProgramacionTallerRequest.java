package microservice.punche.programaciontaller.dtos;

import java.time.LocalDateTime;
import java.util.List;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.programaciontallerfamilia.dtos.CreateProgramacionTallerFamiliaRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateProgramacionTallerRequest {

   @NotNull
   private Long idProgTaller;

   // ? Opcionales: Si taller existe
   private Integer idTaller;

   // ? OPCIONES: Si taller es nuevo
   private Integer idModulo; // * » CEDIF
   private Integer idSesion; // * » PUNCHE
   private Integer idObjetivo; // * » ACERCANDONOS
   private String nombreTaller;

   // TODO:
   @NotEmpty
   @Valid
   private List<CreateProgramacionTallerFamiliaRequest> tallerFamilias;

   // ? Opcionales
   private Integer idPersonal; // * ¿Personal que dicta?
   private Integer idUO; // * ¿Empresa que dicta?

   // ? Opcionales
   private Integer tipoResponsableDictado;
   private String responsablesDictado;

   private String tema;

   @NotBlank
   private String lugarTaller;

   @NotNull
   private LocalDateTime fecHoraIni;

   @NotNull
   private LocalDateTime fecHoraFin;

   @NotNull
   private Integer usuModifica;

}
