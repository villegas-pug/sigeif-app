package microservice.educalle.ejecucionsesion.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.educalle.ejecucionsesion.model.EjecucionSesion;
import microservice.educalle.ejecucionsesion.repository.EjecucionSesionRepository;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class EjecucionSesionServiceImpl implements EjecucionSesionService {

   private final EjecucionSesionRepository repository;

   @Override
   @Transactional
   public EjecucionSesion createEjecucionSesion(EjecucionSesion ejecucionSesion) {
      return this.repository.createEjecucionSesion(ejecucionSesion);
   }

   @Override
   @Transactional
   public EjecucionSesion updateEjecucionSesion(EjecucionSesion ejecucionSesion) {
      return this.repository.updateEjecucionSesion(ejecucionSesion);
   }

   @Override
   @Transactional
   public void deleteEjecucionSesionById(Long idSesion) {
      this.repository.deleteEjecucionSesionById(idSesion);
   }

   @Override
   @Transactional
   public void disableEjecucionSesionById(Long idSesion) {
      this.repository.disableEjecucionSesionById(idSesion);
   }

   @Override
   @Transactional(readOnly = true)
   public EjecucionSesion findEjecucionSesionById(Long idEjecSesion) {
      return this.repository.findEjecucionSesionById(idEjecSesion).orElseThrow(NotFoundException::new);
   }

   @Override
   @Transactional
   public void uploadAnexoEjecucionSesion(Long idEjecSesion, String anexoNombre, byte[] anexo) {
      this.repository.uploadAnexoEjecucionSesion(idEjecSesion, anexoNombre, anexo);
   }

}
