package microservice.punche.zona.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.punche.aliado.model.Aliado;
import microservice.punche.equipotrabajo.model.EquipoTrabajo;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.punche.institucion.model.Institucion;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
import microservice.punche.unidadorganica.model.UnidadOrganica;
import microservice.punche.zona.model.ZonaIntervencion;
import microservice.shared_data.entities.AliadoEntity;
import microservice.shared_data.entities.EquipoTrabajoEntity;
import microservice.shared_data.entities.InstitucionEntity;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.entities.UnidadOrganicaEntity;
import microservice.shared_data.entities.ZonaIntervencionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ZonaIntervencionEntityMapper {

      // * Dep´s
      @Mappings({
                  @Mapping(target = "zonaIntervencion", ignore = true)
      })
      EquipoTrabajo toModel(EquipoTrabajoEntity source);

      @Mappings({
                  @Mapping(target = "zonaIntervencion", ignore = true)
      })
      Aliado toModel(AliadoEntity source);

      @Mappings({
                  @Mapping(target = "potencialesFamilias", ignore = true)
      })
      UnidadOrganica toModel(UnidadOrganicaEntity source);

      @Mappings({
                  @Mapping(target = "familia", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true)
      })
      FamiliaIntegrante toModel(IntegranteFamiliaEntity source);

      @Mappings({
                  @Mapping(target = "zonaIntervencion", ignore = true),
                  // ! @Mapping(target = "aliado", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true),
                  @Mapping(target = "motivosReferencia", ignore = true)
      })
      PotencialFamilia toModel(PotencialFamiliaEntity source);

      // * Model-To-Entity
      @Mappings({
                  @Mapping(target = "institucion", source = "institucion", qualifiedByName = "mapInstitucionToEntity"),
                  @Mapping(target = "unidadOrganica", source = "unidadOrg", qualifiedByName = "mapUnidadOrgToEntity")
      })
      ZonaIntervencionEntity toEntity(ZonaIntervencion source);

      @Mappings({ // ! No depende de `toEntity`
                  @Mapping(target = "institucion", source = "institucion", qualifiedByName = "mapInstitucionToEntity"),
                  @Mapping(target = "unidadOrganica", source = "unidadOrg", qualifiedByName = "mapUnidadOrgToEntity")
      })
      void fromModelToEntity(ZonaIntervencion source, @MappingTarget ZonaIntervencionEntity target);

      // * Entity-To-Model
      ZonaIntervencion toModel(ZonaIntervencionEntity source);

      // * Default method's
      @Named("mapInstitucionToEntity")
      default InstitucionEntity mapInstitucionToEntity(Institucion model) {
            return model != null ? InstitucionEntity.builder().idInstitucion(model.getIdInstitucion()).build() : null;
      }

      @Named("mapUnidadOrgToEntity")
      default UnidadOrganicaEntity mapUnidadOrgToEntity(UnidadOrganica model) {
            return model != null ? UnidadOrganicaEntity.builder().idUO(model.getIdUO()).build() : null;
      }

}
