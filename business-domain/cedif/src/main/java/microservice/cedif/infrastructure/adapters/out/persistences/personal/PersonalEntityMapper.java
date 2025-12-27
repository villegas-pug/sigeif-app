package microservice.cedif.infrastructure.adapters.out.persistences.personal;

import org.mapstruct.Mapper;
import microservice.cedif.domain.models.Personal;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PersonalEntityMapper {
   Personal toModel(PersonalEntity entity);
}
