package microservice.sigesu.ejecucionsesion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.sigesu.ejecucionsesion.dtos.CreateEjecucionSesionRequest;
import microservice.sigesu.ejecucionsesion.model.EjecucionSesion;
import microservice.sigesu.ejecucionsesionintegrante.dtos.CreateEjecucionSesionIntegranteRequest;
import microservice.sigesu.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface EjecucionSesionCreateMapper {

      // * Dep's
      @Mappings({
                  @Mapping(source = "idIntegrante", target = "integranteFamilia.idIntegrante")
      })
      EjecucionSesionIntegrante toModel(CreateEjecucionSesionIntegranteRequest source);

      // * Create-To-Model
      @Mappings({
                  @Mapping(source = "idDetPatfam", target = "detPatfam.idDetPatfam"),
                  @Mapping(source = "idSesion", target = "sesion.idSesion"),
                  @Mapping(source = "idPersonal", target = "personal.idPersonal"),
                  @Mapping(source = "idModalidad", target = "modalidad.idCatalogo")
      })
      EjecucionSesion toModel(CreateEjecucionSesionRequest source);

      // * Default method's

}
