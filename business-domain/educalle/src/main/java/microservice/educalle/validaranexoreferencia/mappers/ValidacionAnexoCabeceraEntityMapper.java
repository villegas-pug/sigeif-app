package microservice.educalle.validaranexoreferencia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import microservice.shared_data.entities.ValidacionAnexoCabeceraEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;
import microservice.educalle.validaranexoreferencia.models.ValidacionAnexoCabecera;

@Mapper(config = BaseMapStructConfig.class)
public interface ValidacionAnexoCabeceraEntityMapper {

   // * Dep´s

   // * Model-To-Entity
   ValidacionAnexoCabeceraEntity toEntity(ValidacionAnexoCabecera model);

   void fromModelToEntity(ValidacionAnexoCabecera source, @MappingTarget ValidacionAnexoCabeceraEntity target);

   // * Entity-To-Model
   ValidacionAnexoCabecera toModel(ValidacionAnexoCabeceraEntity entity);

   // * Default method's

}
