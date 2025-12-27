package microservice.punche.ejecucionsesion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.punche.ejecucionsesion.dtos.UpdateEjecucionSesionRequest;
import microservice.punche.ejecucionsesion.model.EjecucionSesion;
import microservice.punche.ejecucionsesionintegrante.dtos.CreateEjecucionSesionIntegranteRequest;
import microservice.punche.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface EjecucionSesionUpdateMapper {

      // * Dep's
      @Mappings({
                  @Mapping(source = "idIntegrante", target = "integranteFamilia.idIntegrante")
      })
      EjecucionSesionIntegrante toModel(CreateEjecucionSesionIntegranteRequest source);

      // * Create-To-Model
      @Mappings({
                  @Mapping(source = "idModalidad", target = "modalidad.idCatalogo")
      })
      EjecucionSesion toModel(UpdateEjecucionSesionRequest source);

      // * Default method's

}
