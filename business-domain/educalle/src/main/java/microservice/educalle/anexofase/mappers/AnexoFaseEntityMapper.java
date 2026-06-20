package microservice.educalle.anexofase.mappers;

import org.mapstruct.Mapper;

import microservice.shared_data.entities.AnexoFaseEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;
import microservice.educalle.anexofase.model.AnexoFase;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoFaseEntityMapper {

   // * Dep´s

   // * Create-To-Model
   AnexoFase toModel(AnexoFaseEntity entity);

   // * Default method's

}
