package microservice.punche.unidadsesion.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.punche.unidadsesion.mappers.UnidadSesionEntityMapper;
import microservice.punche.unidadsesion.model.UnidadSesion;
import microservice.shared_data.entities.UnidadEntity;
import microservice.shared_data.entities.UnidadSesionEntity;

@Repository
@AllArgsConstructor
public class UnidadSesionRepositoryImpl implements UnidadSesionRepository {

   private final UnidadSesionJpaRepository jpaRepository;
   private final UnidadSesionEntityMapper mapper;

   @Override
   public UnidadSesion save(UnidadSesion sesion) { // TODO: Create & Update

      Integer idSesion = sesion.getIdSesion();
      UnidadSesionEntity sesionEntity = null;

      if (idSesion == null) { // * Nuevo
         sesionEntity = new UnidadSesionEntity();
      } else { // * Actualiza
         sesionEntity = this.jpaRepository.findById(idSesion).get();
      }

      // * Común
      this.mapper.fromModelToEntity(sesion, sesionEntity);

      return this.mapper.toModel(this.jpaRepository.save(sesionEntity));
   }

   @Override
   public List<UnidadSesion> findAllSesionByIdUnidad(Integer idUnidad) {
      return this.jpaRepository.findByUnidad(UnidadEntity.builder().idUnidad(idUnidad).build()).stream()
            .map(this.mapper::toModel).toList();
   }

}
