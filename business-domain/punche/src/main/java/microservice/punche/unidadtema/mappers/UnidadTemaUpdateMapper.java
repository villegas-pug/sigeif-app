package microservice.punche.unidadtema.mappers;

import org.mapstruct.Mapper;
import microservice.punche.taller.model.Taller;
import microservice.punche.unidadtema.dtos.UpdateUnidadTemaRequest;
import microservice.punche.unidadtema.model.UnidadTema;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface UnidadTemaUpdateMapper {

   // * Dep's
   Taller toModel(TallerEntity source);

   // * Entity-To-Entity
   UnidadTema toModel(UpdateUnidadTemaRequest source);

   // * Default method's

}
