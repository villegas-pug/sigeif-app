package microservice.educalle.taller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.educalle.taller.model.Taller;
import microservice.educalle.unidadsesion.model.UnidadSesion;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.entities.UnidadSesionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface TallerEntityMapper {

      // * Model-To-Entity
      @Mappings({
                  @Mapping(source = "sesion", target = "sesion", qualifiedByName = "mapSesionToEnity")
      })
      void fromModelToEntity(Taller source, @MappingTarget TallerEntity target);

      @Mappings({
                  @Mapping(source = "sesion", target = "sesion", qualifiedByName = "mapSesionToEnity")
      })
      TallerEntity toEntity(Taller source);

      @Mapping(target = "sesion", ignore = true)
      Taller toModel(TallerEntity source);

      // * Default method's
      @Named("mapSesionToEnity")
      default UnidadSesionEntity mapSesionToId(UnidadSesion sesion) {
            return sesion != null ? UnidadSesionEntity.builder().idSesion(sesion.getIdSesion()).build() : null;
      }

}
