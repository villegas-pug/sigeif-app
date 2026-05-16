package microservice.sigesu.personal.repository;

import java.util.List;
import java.util.Optional;
import microservice.sigesu.personal.dtos.PersonalDto;
import microservice.shared_data.entities.PersonalEntity;

public interface PersonalRepository {

   Optional<PersonalEntity> findPersonalById(Long idPersonal);

   List<PersonalDto> findPersonalByDocumento(String nroDoc);

   List<PersonalDto> findPersonalByDynamicParam(Integer tipoBusqueda, String dynamicValue);

}
