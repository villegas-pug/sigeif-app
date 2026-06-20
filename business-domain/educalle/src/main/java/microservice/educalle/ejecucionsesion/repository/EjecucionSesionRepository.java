package microservice.educalle.ejecucionsesion.repository;

import java.util.Optional;

import microservice.educalle.ejecucionsesion.model.EjecucionSesion;

public interface EjecucionSesionRepository {

   EjecucionSesion createEjecucionSesion(EjecucionSesion ejecucionSesion);

   EjecucionSesion updateEjecucionSesion(EjecucionSesion ejecucionSesion);

   void deleteEjecucionSesionById(Long idSesion);

   void disableEjecucionSesionById(Long idSesion);

   Optional<EjecucionSesion> findEjecucionSesionById(Long idEjecSesion);

   void uploadAnexoEjecucionSesion(Long idEjecSesion, String anexoNombre, byte[] anexo);

}
