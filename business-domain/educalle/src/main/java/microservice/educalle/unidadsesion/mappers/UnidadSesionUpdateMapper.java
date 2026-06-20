package microservice.educalle.unidadsesion.mappers;

import org.mapstruct.Mapper;
import microservice.educalle.taller.model.Taller;
import microservice.educalle.unidadsesion.dtos.UpdateUnidadSesionRequest;
import microservice.educalle.unidadsesion.model.UnidadSesion;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface UnidadSesionUpdateMapper {

   // * Dep's
   Taller toModel(TallerEntity source);

   // * Entity-To-Entity
   UnidadSesion toModel(UpdateUnidadSesionRequest source);

   // * Default method's

}
