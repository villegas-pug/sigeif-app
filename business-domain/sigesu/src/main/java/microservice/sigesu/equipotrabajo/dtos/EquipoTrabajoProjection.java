package microservice.sigesu.equipotrabajo.dtos;

import microservice.shared_data.entities.Cargo;
import microservice.shared_data.entities.PersonalEntity;

public interface EquipoTrabajoProjection {

   Long getIdEquipo();

   Cargo getCargo();

   PersonalEntity getPersonal();

   Integer getEliminado();

}
