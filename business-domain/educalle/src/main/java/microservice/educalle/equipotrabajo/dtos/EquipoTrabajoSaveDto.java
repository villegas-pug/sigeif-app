package microservice.educalle.equipotrabajo.dtos;

import java.time.LocalDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import microservice.shared_data.entities.Cargo;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.entities.ZonaIntervencionEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = { "idEquipo" })
public class EquipoTrabajoSaveDto {

   private Long idEquipo;

   @NotNull
   ZonaIntervencionEntity zonaIntervencion;

   @NotNull
   PersonalEntity personal;

   @NotNull
   private Cargo cargo;

   @NotNull
   @Positive
   @Min(1)
   private Long usuRegistra;

   private Integer estado;
   private Long usuActualiza;
   private LocalDate fecRegistra;
   private LocalDate fecActualiza;
   private Integer eliminado;

}
