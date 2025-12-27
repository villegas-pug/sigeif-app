package microservice.punche.contacto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import microservice.punche.contacto.dtos.*;
import microservice.shared_data.entities.Contacto;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ContactoMapper {

   void fromCreateDtoToEntity(ContactoCreateRequestDto dto, @MappingTarget Contacto entity);

   void fromUpdateDtoToEntity(ContactoUpdateRequestDto dto, @MappingTarget Contacto entity);

   ContactoResponseDto toResponseDto(Contacto entity);

}
