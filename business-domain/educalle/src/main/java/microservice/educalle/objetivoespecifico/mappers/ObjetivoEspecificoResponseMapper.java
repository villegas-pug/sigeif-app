package microservice.educalle.objetivoespecifico.mappers;

import org.mapstruct.Mapper;

import microservice.educalle.objetivoespecifico.dtos.ObjetivoEspecificoResponse;
import microservice.educalle.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ObjetivoEspecificoResponseMapper {

   ObjetivoEspecificoResponse toResponse(ObjetivoEspecifico source);

}
