package microservice.sigesu.potencialfamilia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.sigesu.personal.model.Personal;
import microservice.sigesu.potencialfamilia.dtos.UpdatePartialPotecialFamiliaRequest;
import microservice.sigesu.potencialfamilia.model.PotencialFamilia;
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
