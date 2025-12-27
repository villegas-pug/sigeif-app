package microservice.punche.programaciontaller.mappers;

import org.mapstruct.Mapper;
import microservice.punche.programaciontaller.dtos.ProgramacionTallerResponse;
import microservice.punche.programaciontaller.model.ProgramacionTaller;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ProgramacionTallerResponseMapper {

      // * Dep´s

      // * Model-To-Response
      ProgramacionTallerResponse toResponse(ProgramacionTaller source);

      // * Default method's

}
