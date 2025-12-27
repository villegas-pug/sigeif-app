package microservice.punche.institucion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import microservice.punche.institucion.dtos.InstitucionCreateRequestDto;
import microservice.punche.institucion.dtos.InstitucionUpdateRequestDto;
import microservice.shared_data.entities.InstitucionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface InstitucionMapper {

   void fromCreateDtoEntity(InstitucionCreateRequestDto dto, @MappingTarget InstitucionEntity entity);

   void fromUpdateDtoEntity(InstitucionUpdateRequestDto dto, @MappingTarget InstitucionEntity entity);

}
