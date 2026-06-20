package microservice.educalle.patfam.service;

import microservice.educalle.patfam.models.Patfam;

public interface PatfamService {
   Patfam createPatfam(Patfam patfam);

   Patfam updatePatfam(Patfam patfam);

   void deletePatfamById(Long idPatfam);

   Patfam findPatfamByIdFamilia(Long idFamilia);
}
