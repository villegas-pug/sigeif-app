package microservice.educalle.unidadsesion.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.educalle.unidadsesion.model.UnidadSesion;
import microservice.educalle.unidadsesion.repository.UnidadSesionRepository;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class UnidadSesionServiceImpl implements UnidadSesionService {

   private final UnidadSesionRepository repository;

   @Override
   @Transactional
   public UnidadSesion createUnidadSesion(UnidadSesion sesion) {
      return this.repository.save(sesion);
   }

   @Override
   @Transactional
   public UnidadSesion updateUnidadSesion(UnidadSesion sesion) {
      return this.repository.save(sesion);
   }

   @Override
   @Transactional(readOnly = true)
   public List<UnidadSesion> findAllSesionByIdUnidad(Integer idUnidad) {
      List<UnidadSesion> sesiones = this.repository.findAllSesionByIdUnidad(idUnidad);
      if (sesiones.isEmpty()) {
         throw new NotFoundException();
      }
      return this.repository.findAllSesionByIdUnidad(idUnidad);
   }

}
