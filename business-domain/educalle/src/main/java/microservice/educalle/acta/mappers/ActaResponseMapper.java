package microservice.educalle.acta.mappers;

import org.mapstruct.Mapper;

import microservice.shared_data.mappers.BaseMapStructConfig;
import microservice.educalle.acta.dto.ActaResponse;
import microservice.educalle.acta.model.Acta;

@Mapper(config = BaseMapStructConfig.class)
public interface ActaResponseMapper {

   // * Dep´s

   // * Model-To-Response
   ActaResponse toResponse(Acta source);

   // * Default method's

}
