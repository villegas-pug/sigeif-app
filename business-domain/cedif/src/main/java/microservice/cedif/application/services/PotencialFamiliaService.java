package microservice.cedif.application.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.models.PotencialFamilia;
import microservice.cedif.domain.ports.in.potencialfamilia.PotencialFamiliaCreateCommand;
import microservice.cedif.domain.ports.in.potencialfamilia.PotencialFamiliaServicePort;
import microservice.cedif.domain.ports.out.PotencialFamiliaRepositoryPort;
import microservice.shared_data.exceptions.NotFoundByIdException;

@Service
@AllArgsConstructor
public class PotencialFamiliaService implements PotencialFamiliaServicePort {

   private final PotencialFamiliaRepositoryPort repository;

   @Override
   @Transactional
   public void createPotecialFamilia(PotencialFamiliaCreateCommand potencialFamilia) {
      this.repository.createPotecialFamilia(potencialFamilia);
   }

   @Override
   @Transactional(readOnly = true)
   public <M> M findPotencialFamiliaById(Long idFamilia) {
      var potencialFamilia = this.repository.findPotencialFamiliaById(idFamilia)
            .orElseThrow(() -> new NotFoundByIdException(idFamilia));

      return (M) potencialFamilia;
   }

   @Override
   @Transactional
   public <M> M updatePotencialFamilia(PotencialFamilia potencialFamilia) {
      this.repository.updatePotencialFamilia(potencialFamilia);
      return (M) potencialFamilia;
   }

   @Override
   @Transactional
   public <M> M deletePotencialFamiliaById(Long idFamilia) {
      M model = this.repository.deletePotencialFamiliaById(idFamilia);
      return (M) model;
   }

   @Override
   @Transactional
   public PotencialFamilia partialUpdatePotecialFamilia(PotencialFamilia potencialFamilia) {
      return this.repository.partialUpdatePotecialFamilia(potencialFamilia);
   }

}
