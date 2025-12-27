package microservice.cedif.infrastructure.adapters.in.web.controllers.unidadorganica;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.cedif.domain.models.UnidadOrganica;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface UnidadOrganicaResponseMapper {

   // * Dep´s

   // * Create-To-Model
   @Mappings({
         @Mapping(target = "representante", source = "representante.persona"),
   })
   UnidadOrganicaResponse toResponse(UnidadOrganica source);

   // * Default method's

}
