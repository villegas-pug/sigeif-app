package microservice.punche.ejecucionsesionintegrante.repository;

import microservice.punche.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;

public interface EjecucionSesionIntegranteRepository {

   EjecucionSesionIntegrante createEjecucionSesion(EjecucionSesionIntegrante ejecucionSesion);

   EjecucionSesionIntegrante updateEjecucionSesion(EjecucionSesionIntegrante ejecucionSesion);

   void deleteEjecucionSesionById(Long idSesion);

   void disableEjecucionSesionById(Long idSesion);

}
