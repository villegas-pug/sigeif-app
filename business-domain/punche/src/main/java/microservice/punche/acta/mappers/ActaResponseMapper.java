package microservice.punche.acta.mappers;

import org.mapstruct.Mapper;

import microservice.punche.acta.dto.ActaResponse;
import microservice.punche.acta.model.Acta;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ActaResponseMapper {

   // * Dep´s

   // * Model-To-Response
   ActaResponse toResponse(Acta source);

   // * Default method's

}
