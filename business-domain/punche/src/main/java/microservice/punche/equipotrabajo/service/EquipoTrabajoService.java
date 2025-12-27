package microservice.punche.equipotrabajo.service;

import java.util.List;

import microservice.punche.equipotrabajo.dtos.EquipoTrabajoResponse;
import microservice.punche.equipotrabajo.model.EquipoTrabajo;

public interface EquipoTrabajoService {

   EquipoTrabajo createEquipoTrabajo(EquipoTrabajo equipoTrabajo);

   EquipoTrabajo updateEquipoTrabajo(EquipoTrabajo equipoTrabajo);

   EquipoTrabajo findEquipoTrabajoById(Long idEquipo);

   void deleteEquipoTrabajoById(Long idEquipo);

   List<EquipoTrabajoResponse> findAcompañantesByIdZona(Long idZona);

}
