package microservice.cedif.domain.ports.in.potencialfamilia;

import microservice.cedif.domain.models.PotencialFamilia;

public interface PotencialFamiliaServicePort {

      void createPotecialFamilia(PotencialFamiliaCreateCommand potencialFamilia);

      <M> M findPotencialFamiliaById(Long idFamilia);

      <M> M updatePotencialFamilia(PotencialFamilia potencialFamilia);

      <M> M deletePotencialFamiliaById(Long idFamilia);

      PotencialFamilia partialUpdatePotecialFamilia(PotencialFamilia potencialFamilia);

}