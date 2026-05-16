package microservice.sigesu.institucion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import microservice.sigesu.institucion.dtos.InstitucionCreateRequestDto;
import microservice.sigesu.institucion.dtos.InstitucionUpdateRequestDto;
import microservice.shared_data.entities.InstitucionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface InstitucionMapper {

   void fromCreateDtoEntity(InstitucionCreateRequestDto dto, @MappingTarget InstitucionEntity entity);

   void fromUpdateDtoEntity(InstitucionUpdateRequestDto dto, @MappingTarget InstitucionEntity entity);

}
