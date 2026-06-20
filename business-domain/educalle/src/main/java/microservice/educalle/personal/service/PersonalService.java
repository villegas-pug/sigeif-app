package microservice.educalle.personal.service;

import java.util.List;
import java.util.Optional;
import microservice.educalle.personal.dtos.PersonalDto;
import microservice.educalle.personal.model.Personal;

public interface PersonalService {

   public List<PersonalDto> findPersonalByDocumento(String nroDoc);

   List<PersonalDto> findPersonalByDynamicParam(Integer tipoBusqueda, String dynamicValue);

   Personal findPersonalById(Integer idPersonal);

}
