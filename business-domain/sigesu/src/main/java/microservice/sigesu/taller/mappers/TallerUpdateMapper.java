package microservice.sigesu.taller.mappers;

import org.mapstruct.Mapper;
import microservice.sigesu.taller.dtos.UpdateTallerRequest;
import microservice.sigesu.taller.model.Taller;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface TallerUpdateMapper {

   // * Model-To-Entity
   Taller toModel(UpdateTallerRequest source);

   // * Default method's

}
