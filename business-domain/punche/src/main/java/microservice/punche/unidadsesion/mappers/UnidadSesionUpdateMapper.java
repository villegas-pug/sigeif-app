package microservice.punche.unidadsesion.mappers;

import org.mapstruct.Mapper;
import microservice.punche.taller.model.Taller;
import microservice.punche.unidadsesion.dtos.UpdateUnidadSesionRequest;
import microservice.punche.unidadsesion.model.UnidadSesion;
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
