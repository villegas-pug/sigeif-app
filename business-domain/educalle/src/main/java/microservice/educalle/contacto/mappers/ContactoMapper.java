package microservice.educalle.contacto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import microservice.educalle.contacto.dtos.*;
import microservice.shared_data.entities.Contacto;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ContactoMapper {

   void fromCreateDtoToEntity(ContactoCreateRequestDto dto, @MappingTarget Contacto entity);

   void fromUpdateDtoToEntity(ContactoUpdateRequestDto dto, @MappingTarget Contacto entity);

   ContactoResponseDto toResponseDto(Contacto entity);

}
