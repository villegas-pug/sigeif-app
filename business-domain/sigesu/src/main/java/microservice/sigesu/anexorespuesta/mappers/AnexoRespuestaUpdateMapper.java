package microservice.sigesu.anexorespuesta.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import microservice.sigesu.anexorespuesta.dtos.UpdateAnexoRespuestaRequest;
import microservice.sigesu.anexorespuesta.model.AnexoRespuesta;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoRespuestaUpdateMapper {

      AnexoRespuesta toModel(UpdateAnexoRespuestaRequest source);

      List<AnexoRespuesta> toModels(List<UpdateAnexoRespuestaRequest> sources);

}
