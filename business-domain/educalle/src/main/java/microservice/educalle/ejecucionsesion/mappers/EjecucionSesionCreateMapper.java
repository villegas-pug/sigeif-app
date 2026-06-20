package microservice.educalle.ejecucionsesion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.educalle.ejecucionsesion.dtos.CreateEjecucionSesionRequest;
import microservice.educalle.ejecucionsesion.model.EjecucionSesion;
import microservice.educalle.ejecucionsesionintegrante.dtos.CreateEjecucionSesionIntegranteRequest;
import microservice.educalle.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;
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
