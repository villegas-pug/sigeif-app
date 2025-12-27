package microservice.punche.equipotrabajo.repository;

import java.util.List;
import java.util.Optional;
import microservice.punche.equipotrabajo.model.EquipoTrabajo;

public interface EquipoTrabajoRepository {

   EquipoTrabajo saveEquipoTrabajo(EquipoTrabajo equipoTrabajo);

   Optional<EquipoTrabajo> findEquipoTrabajoById(Long idEquipo);

   void deleteEquipoTrabajoById(Long idEquipo);

   List<EquipoTrabajo> findAcompañantesByIdZona(Long idZona);

}
