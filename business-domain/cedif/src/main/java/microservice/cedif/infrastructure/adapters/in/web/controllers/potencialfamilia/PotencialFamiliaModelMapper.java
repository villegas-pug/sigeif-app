package microservice.cedif.infrastructure.adapters.in.web.controllers.potencialfamilia;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.cedif.domain.models.PotencialFamilia;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PotencialFamiliaModelMapper {

      @Mappings({
                  @Mapping(source = "idUnidadOrganica", target = "unidadOrganica.idUO")
      })
      PotencialFamilia fromUpdate(PotencialFamiliaUpdateRequest request);

}
