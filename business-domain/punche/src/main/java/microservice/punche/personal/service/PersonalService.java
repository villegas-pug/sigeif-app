package microservice.punche.personal.service;

import java.util.List;

import microservice.punche.personal.dtos.PersonalDto;

public interface PersonalService {

   public List<PersonalDto> findPersonalByDocumento(String nroDoc);

   List<PersonalDto> findPersonalByDynamicParam(Integer tipoBusqueda, String dynamicValue);

}
