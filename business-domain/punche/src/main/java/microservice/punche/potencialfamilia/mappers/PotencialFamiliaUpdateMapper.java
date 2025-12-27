package microservice.punche.potencialfamilia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.punche.personal.model.Personal;
import microservice.punche.potencialfamilia.dtos.UpdatePartialPotecialFamiliaRequest;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
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
