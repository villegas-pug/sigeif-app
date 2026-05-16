package microservice.punche.unidadtema.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.punche.unidadtema.mappers.UnidadTemaEntityMapper;
import microservice.punche.unidadtema.model.UnidadTema;
import microservice.shared_data.entities.UnidadEntity;
import microservice.shared_data.entities.UnidadTemaEntity;

@Repository
@AllArgsConstructor
public class UnidadTemaRepositoryImpl implements UnidadTemaRepository {

   private final UnidadTemaJpaRepository jpaRepository;
   private final UnidadTemaEntityMapper mapper;

   @Override
   public UnidadTema save(UnidadTema sesion) { // TODO: Create & Update

      Integer idTema = sesion.getIdTema();
      UnidadTemaEntity sesionEntity = null;

      if (idTema == null) { // * Nuevo
         sesionEntity = new UnidadTemaEntity();
      } else { // * Actualiza
         sesionEntity = this.jpaRepository.findById(idTema).get();
      }

      // * Común
      this.mapper.fromModelToEntity(sesion, sesionEntity);

      return this.mapper.toModel(this.jpaRepository.save(sesionEntity));
   }

   @Override
   public List<UnidadTema> findAllTemaByIdUnidad(Integer idUnidad) {
      return this.jpaRepository.findByUnidad(UnidadEntity.builder().idUnidad(idUnidad).build()).stream()
            .map(this.mapper::toModel).toList();
   }

}
