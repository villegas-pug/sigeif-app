package microservice.punche.motivoreferecia.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import microservice.punche.motivoreferecia.model.MotivoReferecia;
import microservice.shared_data.entities.MotivoReferenciaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface MotivoReferenciaEntityMapper {

   MotivoReferecia toModel(MotivoReferenciaEntity entity);

   List<MotivoReferecia> toModels(List<MotivoReferenciaEntity> entities);

}
