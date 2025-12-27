package microservice.punche.aliado.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.punche.aliado.dtos.AliadoResponse;
import microservice.punche.aliado.model.Aliado;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AliadoResponseMapper {

      @Mappings({
                  @Mapping(source = "institucion.idInstitucion", target = "idInstitucion"),
                  @Mapping(source = "institucion.nombreReferencia", target = "nombreInstitucion"),
      })
      AliadoResponse toResponse(Aliado nombreInstitucion);

}
