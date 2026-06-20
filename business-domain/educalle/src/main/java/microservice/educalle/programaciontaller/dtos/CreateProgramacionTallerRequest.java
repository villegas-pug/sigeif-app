package microservice.educalle.programaciontaller.dtos;

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
import microservice.educalle.programaciontallerfamilia.dtos.CreateProgramacionTallerFamiliaRequest;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProgramacionTallerRequest {

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
   private Long idPersonal; // * ¿Personal que dicta?
   private Long idUO; // * ¿Empresa que dicta?

   @NotBlank
   private String tema;

   @NotBlank
   private String lugarTaller;

   @NotNull
   private LocalDateTime fecHoraIni;

   @NotNull
   private LocalDateTime fecHoraFin;

   @NotNull
   private Integer usuRegistra;

}
