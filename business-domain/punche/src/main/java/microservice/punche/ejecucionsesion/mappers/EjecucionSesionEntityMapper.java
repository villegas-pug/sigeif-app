package microservice.punche.ejecucionsesion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import microservice.punche.catalogo.model.Catalogo;
import microservice.punche.ejecucionsesion.model.EjecucionSesion;
import microservice.punche.personal.model.Personal;
import microservice.shared_data.entities.CatalogoEntity;
import microservice.shared_data.entities.EjecucionSesionEntity;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface EjecucionSesionEntityMapper {

      // * Entity-To-Model
      @Mappings({
                  @Mapping(target = "detPatfam", ignore = true),
                  @Mapping(target = "sesion", ignore = true),
                  @Mapping(target = "personal", ignore = true),
                  @Mapping(target = "integrantes", ignore = true),
      })
      EjecucionSesion toModel(EjecucionSesionEntity source);

      // * Model-To-Entity
      @Mappings({
                  @Mapping(target = "integrantes", ignore = true),
                  @Mapping(source = "modalidad", target = "modalidad", qualifiedByName = "mapModalidadToEntity"),
                  @Mapping(source = "personal", target = "personal", qualifiedByName = "mapPersonalToEntity")
      })
      void fromModelToEntity(EjecucionSesion source, @MappingTarget EjecucionSesionEntity target);

      // * Default method's
      @Named("mapModalidadToEntity")
      default CatalogoEntity mapModalidadToEntity(Catalogo modalidad) {
            return modalidad != null ? CatalogoEntity.builder().idCatalogo(modalidad.getIdCatalogo()).build() : null;
      }

      @Named("mapPersonalToEntity")
      default PersonalEntity mapPersonalToEntity(Personal personal) {
            return personal != null ? PersonalEntity.builder().idPersonal(personal.getIdPersonal()).build() : null;
      }

}
