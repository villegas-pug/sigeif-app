package microservice.punche.objetivoespecifico.mappers;

import org.mapstruct.Mapper;

import microservice.punche.objetivoespecifico.dtos.ObjetivoEspecificoResponse;
import microservice.punche.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ObjetivoEspecificoResponseMapper {

   ObjetivoEspecificoResponse toResponse(ObjetivoEspecifico source);

}
