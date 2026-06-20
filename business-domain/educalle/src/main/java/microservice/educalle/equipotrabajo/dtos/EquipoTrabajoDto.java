package microservice.educalle.equipotrabajo.dtos;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.shared_data.entities.Cargo;
import microservice.shared_data.entities.PersonalEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoTrabajoDto {

   private Long idEquipo;
   private String idPersonal;
   private String idCargo;
   private Integer idUsuRegistra;
   private Cargo cargo;
   PersonalEntity personal;
   private Long usuActualiza;
   private LocalDate fecActualiza;
   private Integer estado;
   private Integer eliminado;

}
