package microservice.punche.unidadtema.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import microservice.punche.taller.model.Taller;
import microservice.punche.unidadtema.model.UnidadTema;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.entities.UnidadTemaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface UnidadTemaEntityMapper {

      // * Dep's
      Taller toModel(TallerEntity source);

      // * Model-To-Entity
      void fromModelToEntity(UnidadTema source, @MappingTarget UnidadTemaEntity target);

      UnidadTemaEntity toEntity(UnidadTema source);

      // * Entity-To-Model
      @Mappings({
                  @Mapping(target = "talleres", ignore = true),
                  @Mapping(target = "unidad", ignore = true)
      })
      UnidadTema toModel(UnidadTemaEntity source);

}
