package microservice.cedif.application.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import microservice.cedif.domain.models.Personal;
import microservice.cedif.domain.ports.in.personal.PersonalServicePort;
import microservice.cedif.domain.ports.out.PersonalRepositoryPort;
import microservice.shared_data.exceptions.NotFoundException;

@Service
public class PersonalService implements PersonalServicePort {

   @Autowired
   private PersonalRepositoryPort repository;

   @Override
   @Transactional(readOnly = true)
   public List<Personal> findPersonalByDocumento(String nroDoc) {
      List<Personal> personales = this.repository.findPersonalByDocumento(nroDoc);
      if (personales.size() == 0) {
         throw new NotFoundException();
      }
      return personales;
   }

   @Override
   @Transactional(readOnly = true)
   public List<Personal> findPersonalByParams(String nroDoc, String nombres) {
      List<Personal> personales = this.repository.findPersonalByParams(nroDoc, nombres);
      if (personales.size() == 0) {
         throw new NotFoundException();
      }
      return personales;
   }

}
