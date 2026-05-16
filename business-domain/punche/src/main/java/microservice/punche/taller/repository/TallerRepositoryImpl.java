package microservice.punche.taller.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.punche.taller.mappers.TallerEntityMapper;
import microservice.punche.taller.model.Taller;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.entities.UnidadSesionEntity;

@Repository
@AllArgsConstructor
public class TallerRepositoryImpl implements TallerRepository {

   private final TallerJpaRepository jpaRepository;
   private final TallerEntityMapper mapper;

   @Override
   public Taller save(Taller taller) { // TODO: Create & update

      Integer idTaller = taller.getIdTaller();
      TallerEntity tallerEntity = null;

      if (idTaller == null) { // * Nuevo
         tallerEntity = new TallerEntity();
      } else { // * Actualiza
         tallerEntity = this.jpaRepository.findById(idTaller).get();
      }

      // * Común
      this.mapper.fromModelToEntity(taller, tallerEntity);

      return this.mapper.toModel(this.jpaRepository.save(tallerEntity));
   }

   @Override
   public List<Taller> findAllTallerByIdSesion(Integer idSesion) {
      return this.jpaRepository.findBySesion(UnidadSesionEntity.builder().idSesion(idSesion).build()).stream()
            .map(this.mapper::toModel).toList();
   }

   @Override
   public List<Taller> findAllTallers() {
      return this.jpaRepository.findAll().stream().map(this.mapper::toModel).toList();
   }

}
