package microservice.educalle.acta.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import microservice.shared_data.entities.ActaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;
import microservice.educalle.acta.model.Acta;

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
