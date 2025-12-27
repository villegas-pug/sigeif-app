package microservice.cedif.infrastructure.adapters.in.web.controllers.potencialfamilia;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.cedif.domain.models.Personal;
import microservice.cedif.domain.models.PotencialFamilia;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PotencialFamiliaUpdateMapper {

      // * Dep´s

      // * Create-To-Model
      @Mappings({
                  @Mapping(source = "idPersonal", target = "acompañante", qualifiedByName = "mapIdPersonalToPersonal")
      })
      PotencialFamilia toModel(UpdatePartialPotecialFamiliaRequest request);

      // * Default method's
      @Named("mapIdPersonalToPersonal")
      default Personal mapIdPersonalToPersonal(Long idPersonal) {
            return idPersonal != null ? Personal
                        .builder()
                        .idPersonal(idPersonal)
                        .build() : null;

      }

}
