package microservice.punche.ejecucionsesion.repository;

import java.util.Optional;

import microservice.punche.ejecucionsesion.model.EjecucionSesion;

public interface EjecucionSesionRepository {

   EjecucionSesion createEjecucionSesion(EjecucionSesion ejecucionSesion);

   EjecucionSesion updateEjecucionSesion(EjecucionSesion ejecucionSesion);

   void deleteEjecucionSesionById(Long idSesion);

   void disableEjecucionSesionById(Long idSesion);

   Optional<EjecucionSesion> findEjecucionSesionById(Long idEjecSesion);

   void uploadAnexoEjecucionSesion(Long idEjecSesion, String anexoNombre, byte[] anexo);

}
