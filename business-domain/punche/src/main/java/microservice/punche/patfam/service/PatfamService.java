package microservice.punche.patfam.service;

import microservice.punche.patfam.models.Patfam;

public interface PatfamService {
   Patfam createPatfam(Patfam patfam);

   Patfam updatePatfam(Patfam patfam);

   void deletePatfamById(Long idPatfam);

   Patfam findPatfamByIdFamilia(Long idFamilia);
}
