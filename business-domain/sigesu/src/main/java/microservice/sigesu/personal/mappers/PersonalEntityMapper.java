package microservice.sigesu.personal.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.shared_data.entities.PersonaEntity;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;
import microservice.sigesu.persona.model.Persona;
import microservice.sigesu.personal.model.Personal;

@Mapper(config = BaseMapStructConfig.class)
public interface PersonalEntityMapper {

   // * Dep´s
   @Mappings({
         @Mapping(target = "usuario.persona", ignore = true),
   })
   Persona toModel(PersonaEntity entity);

   // * Create-To-Model
   Personal toModel(PersonalEntity entity);

   // * Default method's

}
