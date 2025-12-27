package microservice.cedif.domain.ports.out;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import microservice.cedif.domain.models.PotencialFamilia;
import microservice.cedif.domain.ports.in.potencialfamilia.PotencialFamiliaCreateCommand;

public interface PotencialFamiliaRepositoryPort {

   void createPotecialFamilia(PotencialFamiliaCreateCommand potencialFamilia);

   Optional<PotencialFamilia> findPotencialFamiliaById(Long idFamilia);

   <M> M updatePotencialFamilia(PotencialFamilia potencialFamilia);

   <M> M deletePotencialFamiliaById(Long idFamilia);

   List<PotencialFamilia> findAllPotencialesFamilias();

   List<PotencialFamilia> findPotencialesFamiliasByIds(Set<Long> idsFamilia);

   PotencialFamilia partialUpdatePotecialFamilia(PotencialFamilia potencialFamilia);

}
