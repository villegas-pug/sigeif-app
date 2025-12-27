package microservice.cedif.infrastructure.adapters.out.persistences.unidadorganica;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.models.PotencialFamilia;
import microservice.cedif.domain.models.UnidadOrganica;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.entities.UnidadOrganicaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface UnidadOrganicaEntityMapper {

      // * Dep´s
      @Mappings({
                  @Mapping(target = "familia", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true),
      })
      FamiliaIntegrante toModel(IntegranteFamiliaEntity source);

      @Mappings({
                  @Mapping(target = "anexosRespuestas", ignore = true),
                  @Mapping(target = "unidadOrganica", ignore = true),
      })
      PotencialFamilia toModel(PotencialFamiliaEntity source);

      // * Entity-To-Model
      @Mappings({
                  @Mapping(target = "representante", source = "representante.persona"),
      })
      UnidadOrganica toModel(UnidadOrganicaEntity source);

      @Mappings({
                  @Mapping(target = "representante", source = "representante.persona"),
      })
      UnidadOrganica toModel(UnidadOrganicaProjection source);

      List<UnidadOrganica> toModels(List<UnidadOrganicaEntity> sources);

      // * Default method's

}
