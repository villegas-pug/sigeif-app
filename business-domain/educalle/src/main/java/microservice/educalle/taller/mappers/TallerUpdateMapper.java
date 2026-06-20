package microservice.educalle.taller.mappers;

import org.mapstruct.Mapper;
import microservice.educalle.taller.dtos.UpdateTallerRequest;
import microservice.educalle.taller.model.Taller;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface TallerUpdateMapper {

   // * Model-To-Entity
   Taller toModel(UpdateTallerRequest source);

   // * Default method's

}
