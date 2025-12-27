package microservice.punche.anexofase.mappers;

import org.mapstruct.Mapper;

import microservice.punche.anexofase.model.AnexoFase;
import microservice.shared_data.entities.AnexoFaseEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoFaseEntityMapper {

   // * Dep´s

   // * Create-To-Model
   AnexoFase toModel(AnexoFaseEntity entity);

   // * Default method's

}
