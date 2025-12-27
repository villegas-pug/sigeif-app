package microservice.cedif.infrastructure.adapters.out.persistences.potencialfamilia;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.cedif.domain.models.AnexoRespuesta;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.models.Personal;
import microservice.cedif.domain.models.PotencialFamilia;
import microservice.cedif.domain.models.UnidadOrganica;
import microservice.shared_data.entities.AnexoRespuestaEntity;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.entities.UnidadOrganicaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PotencialFamiliaEntityMapper {

      // * Dep´s: Models
      @Mapping(target = "potencialesFamilias", ignore = true)
      UnidadOrganica toUnidadOrganicaModel(UnidadOrganicaEntity entity);

      @Mappings({
                  @Mapping(target = "familia", ignore = true),
                  @Mapping(target = "integrante", ignore = true)
      })
      AnexoRespuesta toModel(AnexoRespuestaEntity source);

      @Mappings({ // ! Recusión exception
                  @Mapping(target = "familia", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true),
      })
      FamiliaIntegrante toModel(IntegranteFamiliaEntity source);

      // * Model-To-Entity
      @Mappings({
                  @Mapping(target = "unidadOrganica", ignore = true),
                  @Mapping(source = "acompañante", target = "acompañante", qualifiedByName = "mapPersonalToEntity")
      })
      void fromModelToEntity(PotencialFamilia model, @MappingTarget PotencialFamiliaEntity entity);

      PotencialFamiliaEntity toEntity(PotencialFamilia source);

      // * Entity-To-Model
      PotencialFamilia toModel(PotencialFamiliaEntity entity);

      List<PotencialFamilia> toModels(List<PotencialFamiliaEntity> entities);

      Set<PotencialFamilia> toModels(Set<PotencialFamiliaEntity> entities);

      // * Default method's
      @Named("mapPersonalToEntity")
      default PersonalEntity mapPersonalToEntity(Personal personal) {
            return personal != null ? PersonalEntity.builder().idPersonal(personal.getIdPersonal()).build() : null;
      }
}
