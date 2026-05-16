package microservice.sigesu.zona.dtos;

import java.util.List;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.sigesu.aliado.model.AliadoDto;
import microservice.sigesu.equipotrabajo.dtos.EquipoTrabajoDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZonaIntervencionSaveDto {

   ZonaIntervencionResponse zonaIntervencion;
   List<EquipoTrabajoDto> equiposTrabajo;

   @Valid
   List<AliadoDto> aliados;
}
