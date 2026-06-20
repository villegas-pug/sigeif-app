package microservice.educalle.personal.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.exceptions.NotFoundException;
import microservice.educalle.personal.dtos.PersonalDto;
import microservice.educalle.personal.mappers.PersonalEntityMapper;
import microservice.educalle.personal.model.Personal;
import microservice.educalle.personal.repository.PersonalRepository;

@Service
@AllArgsConstructor
public class PersonalServiceImpl implements PersonalService {

   private final PersonalRepository repository;
   private final PersonalEntityMapper mapper;

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

   @Override
   public Personal findPersonalById(Integer idPersonal) {
      Optional<PersonalEntity> personalE = this.repository.findPersonalById(idPersonal);

      Personal personal = personalE
            .map(this.mapper::toModel)
            .orElseThrow(NotFoundException::new);

      return personal;
   }

}
