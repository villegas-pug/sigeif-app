package microservice.punche.unidadtema.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.unidadtema.model.UnidadTema;
import microservice.punche.unidadtema.repository.UnidadTemaRepository;
import microservice.shared_data.exceptions.NotFoundException;

@Service
@AllArgsConstructor
public class UnidadTemaServiceImpl implements UnidadTemaService {

   private final UnidadTemaRepository repository;

   @Override
   @Transactional
   public UnidadTema createUnidadTema(UnidadTema sesion) {
      return this.repository.save(sesion);
   }

   @Override
   @Transactional
   public UnidadTema updateUnidadTema(UnidadTema sesion) {
      return this.repository.save(sesion);
   }

   @Override
   @Transactional(readOnly = true)
   public List<UnidadTema> findAllTemaByIdUnidad(Integer idUnidad) {
      List<UnidadTema> sesiones = this.repository.findAllTemaByIdUnidad(idUnidad);
      if (sesiones.isEmpty()) {
         throw new NotFoundException();
      }
      return this.repository.findAllTemaByIdUnidad(idUnidad);
   }

}
