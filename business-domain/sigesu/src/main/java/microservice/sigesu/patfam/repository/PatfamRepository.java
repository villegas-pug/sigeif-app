package microservice.sigesu.patfam.repository;

import java.util.Optional;

import microservice.sigesu.patfam.models.Patfam;

public interface PatfamRepository {

   Patfam createPatfam(Patfam patfam);

   Patfam updatePatfam(Patfam patfam);

   void deletePatfamById(Long idPatfam);

   Optional<Patfam> findPatfamByIdFamilia(Long idFamilia);

}
