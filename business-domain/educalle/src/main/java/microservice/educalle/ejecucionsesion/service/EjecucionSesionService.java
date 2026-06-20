package microservice.educalle.ejecucionsesion.service;

import java.util.Optional;

import microservice.educalle.ejecucionsesion.model.EjecucionSesion;

public interface EjecucionSesionService {

   EjecucionSesion createEjecucionSesion(EjecucionSesion ejecucionSesion);

   EjecucionSesion updateEjecucionSesion(EjecucionSesion ejecucionSesion);

   void deleteEjecucionSesionById(Long idSesion);

   void disableEjecucionSesionById(Long idSesion);

   EjecucionSesion findEjecucionSesionById(Long idEjecSesion);

   void uploadAnexoEjecucionSesion(Long idEjecSesion, String anexoNombre, byte[] anexo);
}
