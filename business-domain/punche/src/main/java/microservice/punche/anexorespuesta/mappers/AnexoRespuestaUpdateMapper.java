package microservice.punche.anexorespuesta.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import microservice.punche.anexorespuesta.dtos.UpdateAnexoRespuestaRequest;
import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoRespuestaUpdateMapper {

      AnexoRespuesta toModel(UpdateAnexoRespuestaRequest source);

      List<AnexoRespuesta> toModels(List<UpdateAnexoRespuestaRequest> sources);

}
