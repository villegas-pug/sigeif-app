package microservice.sigesu.personal.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.shared_data.exceptions.NotFoundException;
import microservice.sigesu.personal.dtos.PersonalDto;
import microservice.sigesu.personal.repository.PersonalRepository;

@Service
@AllArgsConstructor
public class PersonalServiceImpl implements PersonalService {

   private final PersonalRepository repository;

   @Override
   @Transactional(readOnly = true)
   public List<PersonalDto> findPersonalByDocumento(String nroDoc) {
      List<PersonalDto> personales = this.repository.findPersonalByDocumento(nroDoc);
      if (personales.size() == 0) {
         throw new NotFoundException();
      }
      return personales;
   }

   @Override
   @Transactional(readOnly = true)
   public List<PersonalDto> findPersonalByDynamicParam(Integer tipoBusqueda, String dynamicValue) {
      List<PersonalDto> personales = this.repository.findPersonalByDynamicParam(tipoBusqueda, dynamicValue);
      if (personales.size() == 0) {
         throw new NotFoundException();
      }
      return personales;
   }

}
