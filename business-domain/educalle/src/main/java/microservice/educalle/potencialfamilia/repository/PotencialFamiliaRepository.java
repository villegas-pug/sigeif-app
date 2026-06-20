package microservice.educalle.potencialfamilia.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import microservice.educalle.potencialfamilia.dtos.PotencialFamiliaResponse;
import microservice.educalle.potencialfamilia.model.PotencialFamilia;

public interface PotencialFamiliaRepository {

   void savePotecialFamilia(PotencialFamilia potencialFamilia);

   PotencialFamilia partialUpdatePotecialFamilia(PotencialFamilia potencialFamilia);

   Optional<PotencialFamiliaResponse> findPotencialFamiliaById(Long idFamilia);

   List<PotencialFamiliaResponse> findPotencialesFamiliasByIds(Set<Long> idsFamilia);

   void deletePotencialFamiliaById(Long idFamilia);

}
