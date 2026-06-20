package microservice.educalle.aliado.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.shared_data.mappers.BaseMapStructConfig;
import microservice.educalle.aliado.dtos.AliadoResponse;
import microservice.educalle.aliado.model.Aliado;

@Mapper(config = BaseMapStructConfig.class)
public interface AliadoResponseMapper {

      @Mappings({
                  @Mapping(source = "institucion.idInstitucion", target = "idInstitucion"),
                  @Mapping(source = "institucion.nombreReferencia", target = "nombreInstitucion"),
      })
      AliadoResponse toResponse(Aliado nombreInstitucion);

}
