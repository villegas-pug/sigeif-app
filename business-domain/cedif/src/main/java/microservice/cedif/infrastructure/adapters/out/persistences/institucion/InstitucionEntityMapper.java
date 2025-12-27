package microservice.cedif.infrastructure.adapters.out.persistences.institucion;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import microservice.cedif.domain.models.Institucion;
import microservice.shared_data.entities.InstitucionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface InstitucionEntityMapper {

   // * Dep´s

   // * Entity-To-Model
   List<Institucion> toModels(List<InstitucionEntity> entity);

   void fromEntityToModel(InstitucionEntity entity, @MappingTarget Institucion model);

   // * Entity-To-Model

   // * Default method's

}
