package microservice.cedif.infrastructure.adapters.out.persistences.persona;

import org.mapstruct.Mapper;
import microservice.cedif.domain.models.Persona;
import microservice.shared_data.entities.PersonaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PersonaEntityMapper {

   Persona toModel(PersonaEntity entity);

}
