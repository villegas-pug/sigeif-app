package microservice.punche.ejecucionsesionintegrante.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.punche.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.shared_data.entities.EjecucionSesionIntegranteEntity;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface EjecucionSesionIntegranteEntityMapper {

      // * Dep´s

      // * Model-To-Entity
      @Mappings({
                  @Mapping(source = "integranteFamilia", target = "integranteFamilia", qualifiedByName = "mapIntegranteFamiliaToEntity")
      })
      EjecucionSesionIntegranteEntity toEntity(EjecucionSesionIntegrante source);

      @Mappings({
                  @Mapping(source = "integranteFamilia", target = "integranteFamilia", qualifiedByName = "mapIntegranteFamiliaToEntity")
      })
      void fromModelToEntity(EjecucionSesionIntegrante source, @MappingTarget EjecucionSesionIntegranteEntity target);

      // * Entity-To-Model

      // * Default method's
      @Named("mapIntegranteFamiliaToEntity")
      default IntegranteFamiliaEntity mapIntegranteFamiliaToEntity(FamiliaIntegrante integranteFamilia) {
            return integranteFamilia != null
                        ? IntegranteFamiliaEntity.builder().idIntegrante(integranteFamilia.getIdIntegrante()).build()
                        : null;
      }

}
