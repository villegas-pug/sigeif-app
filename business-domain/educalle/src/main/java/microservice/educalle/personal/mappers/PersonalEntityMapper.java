package microservice.educalle.personal.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.shared_data.entities.PersonaEntity;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.entities.UsuarioEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;
import microservice.educalle.persona.model.Persona;
import microservice.educalle.persona.model.Usuario;
import microservice.educalle.personal.model.Personal;

@Mapper(config = BaseMapStructConfig.class)
public interface PersonalEntityMapper {

      // * Dep´s
      @Mappings({
                  @Mapping(target = "persona", ignore = true),
      })
      Usuario toModel(UsuarioEntity entity);

      // List<Usuario> toModels(List<UsuarioEntity> entities);

      Persona toModel(PersonaEntity entity);

      // * Create-To-Model
      Personal toModel(PersonalEntity entity);

      // * Default method's

}
