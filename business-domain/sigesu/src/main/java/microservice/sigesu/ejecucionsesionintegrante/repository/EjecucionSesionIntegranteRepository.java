package microservice.sigesu.ejecucionsesionintegrante.repository;

import microservice.sigesu.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;

public interface EjecucionSesionIntegranteRepository {

   EjecucionSesionIntegrante createEjecucionSesion(EjecucionSesionIntegrante ejecucionSesion);

   EjecucionSesionIntegrante updateEjecucionSesion(EjecucionSesionIntegrante ejecucionSesion);

   void deleteEjecucionSesionById(Long idSesion);

   void disableEjecucionSesionById(Long idSesion);

}
