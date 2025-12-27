package microservice.punche.acta.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import microservice.punche.acta.model.Acta;
import microservice.shared_data.entities.ActaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ActaEntityMapper {

   // * Dep´s

   // * Entity-To-Model
   void fromModelToEntity(Acta source, @MappingTarget ActaEntity target);

   // * Model-To-Entity
   @Mappings({
         @Mapping(target = "aliado", ignore = true)
   })
   Acta toModel(ActaEntity source);

   // * Default method's

}
