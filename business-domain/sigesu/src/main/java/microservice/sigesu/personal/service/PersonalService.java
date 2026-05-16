package microservice.sigesu.personal.service;

import java.util.List;

import microservice.sigesu.personal.dtos.PersonalDto;

public interface PersonalService {

   public List<PersonalDto> findPersonalByDocumento(String nroDoc);

   List<PersonalDto> findPersonalByDynamicParam(Integer tipoBusqueda, String dynamicValue);

}
