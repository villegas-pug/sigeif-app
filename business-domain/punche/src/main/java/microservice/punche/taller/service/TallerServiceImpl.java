package microservice.punche.taller.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.taller.model.Taller;
import microservice.punche.taller.repository.TallerRepository;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class TallerServiceImpl implements TallerService {

   private final TallerRepository repository;

   @Override
   @Transactional
   public Taller createTaller(Taller taller) {
      return this.repository.save(taller);
   }

   @Override
   @Transactional
   public Taller updateTaller(Taller taller) {
      return this.repository.save(taller);
   }

   @Override
   @Transactional(readOnly = true)
   public List<Taller> findAllTallerByIdSesion(Integer idSesion) {
      List<Taller> talleres = this.repository.findAllTallerByIdSesion(idSesion);
      if (talleres.isEmpty()) {
         throw new NotFoundException();
      }
      return talleres;
   }

   @Override
   @Transactional(readOnly = true)
   public List<Taller> findAllTallers() {
      List<Taller> talleres = this.repository.findAllTallers();
      if (talleres.isEmpty()) {
         throw new NotFoundException();
      }

      return talleres;

   }

}
