package microservice.sigesu.ejecucionsesion.repository;

import java.util.Optional;

import microservice.sigesu.ejecucionsesion.model.EjecucionSesion;

public interface EjecucionSesionRepository {

   EjecucionSesion createEjecucionSesion(EjecucionSesion ejecucionSesion);

   EjecucionSesion updateEjecucionSesion(EjecucionSesion ejecucionSesion);

   void deleteEjecucionSesionById(Long idSesion);

   void disableEjecucionSesionById(Long idSesion);

   Optional<EjecucionSesion> findEjecucionSesionById(Long idEjecSesion);

   void uploadAnexoEjecucionSesion(Long idEjecSesion, String anexoNombre, byte[] anexo);

}
