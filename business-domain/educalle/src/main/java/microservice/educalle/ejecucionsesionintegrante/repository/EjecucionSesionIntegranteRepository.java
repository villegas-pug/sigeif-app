package microservice.educalle.ejecucionsesionintegrante.repository;

import microservice.educalle.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;

public interface EjecucionSesionIntegranteRepository {

   EjecucionSesionIntegrante createEjecucionSesion(EjecucionSesionIntegrante ejecucionSesion);

   EjecucionSesionIntegrante updateEjecucionSesion(EjecucionSesionIntegrante ejecucionSesion);

   void deleteEjecucionSesionById(Long idSesion);

   void disableEjecucionSesionById(Long idSesion);

}
