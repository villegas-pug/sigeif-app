package microservice.sigesu.ejecucionsesion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.sigesu.ejecucionsesion.dtos.UpdateEjecucionSesionRequest;
import microservice.sigesu.ejecucionsesion.model.EjecucionSesion;
import microservice.sigesu.ejecucionsesionintegrante.dtos.CreateEjecucionSesionIntegranteRequest;
import microservice.sigesu.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;
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
