package microservice.punche.zona.dtos;

import java.util.List;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import microservice.punche.aliado.model.AliadoDto;
import microservice.punche.equipotrabajo.dtos.EquipoTrabajoDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZonaIntervencionSaveDto {

   ZonaIntervencionResponse zonaIntervencion;
   List<EquipoTrabajoDto> equiposTrabajo;

   @Valid
   List<AliadoDto> aliados;
}
