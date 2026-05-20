package microservice.sigesu.validaranexoreferencia.repositories;

import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import microservice.shared_data.entities.ValidacionAnexoCabeceraEntity;
import microservice.sigesu.validaranexoreferencia.mappers.ValidacionAnexoCabeceraEntityMapper;
import microservice.sigesu.validaranexoreferencia.models.ValidacionAnexoCabecera;

@Repository
@AllArgsConstructor
public class ValidacionAnexoCabeceraRepositoryImpl implements ValidacionAnexoCabeceraRepository {

   private ValidacionAnexoCabeceraJpaRepository repository;
   private ValidacionAnexoCabeceraEntityMapper mapper;

   @Override
   public ValidacionAnexoCabecera saveValidacionAnexoCabecera(ValidacionAnexoCabecera validacionAnexoCabecera) {
      ValidacionAnexoCabeceraEntity entity = new ValidacionAnexoCabeceraEntity();
      this.mapper.fromModelToEntity(validacionAnexoCabecera, entity);
      ValidacionAnexoCabeceraEntity saved = this.repository.save(entity);
      return this.mapper.toModel(saved);
   }

}
