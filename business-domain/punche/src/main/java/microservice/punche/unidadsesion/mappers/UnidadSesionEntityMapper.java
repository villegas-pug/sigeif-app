package microservice.punche.unidadsesion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import microservice.punche.taller.model.Taller;
import microservice.punche.unidadsesion.model.UnidadSesion;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.entities.UnidadSesionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface UnidadSesionEntityMapper {

      // * Dep's
      Taller toModel(TallerEntity source);

      // * Model-To-Entity
      void fromModelToEntity(UnidadSesion source, @MappingTarget UnidadSesionEntity target);

      UnidadSesionEntity toEntity(UnidadSesion source);

      // * Entity-To-Model
      @Mappings({
                  @Mapping(target = "talleres", ignore = true),
                  @Mapping(target = "unidad", ignore = true)
      })
      UnidadSesion toModel(UnidadSesionEntity source);

}
