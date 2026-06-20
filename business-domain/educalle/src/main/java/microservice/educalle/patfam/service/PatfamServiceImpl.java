package microservice.educalle.patfam.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.educalle.patfam.models.Patfam;
import microservice.educalle.patfam.repository.PatfamRepository;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class PatfamServiceImpl implements PatfamService {

   private final PatfamRepository repository;

   @Override
   @Transactional
   public Patfam createPatfam(Patfam patfam) {
      return this.repository.createPatfam(patfam);
   }

   @Override
   @Transactional(readOnly = true)
   public Patfam findPatfamByIdFamilia(Long idFamilia) {
      Patfam patfam = this.repository.findPatfamByIdFamilia(idFamilia).orElseThrow(NotFoundException::new);
      return patfam;
   }

   @Override
   @Transactional
   public Patfam updatePatfam(Patfam patfam) {
      return this.repository.updatePatfam(patfam);
   }

   @Override
   @Transactional
   public void deletePatfamById(Long idPatfam) {
      this.repository.deletePatfamById(idPatfam);
   }

}
