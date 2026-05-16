package microservice.sigesu.objetivoespecifico.mappers;

import org.mapstruct.Mapper;

import microservice.sigesu.objetivoespecifico.dtos.ObjetivoEspecificoResponse;
import microservice.sigesu.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ObjetivoEspecificoResponseMapper {

   ObjetivoEspecificoResponse toResponse(ObjetivoEspecifico source);

}
