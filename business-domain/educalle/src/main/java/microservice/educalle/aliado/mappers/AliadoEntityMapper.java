package microservice.educalle.aliado.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.springframework.boot.context.properties.bind.Name;

import microservice.shared_data.entities.AliadoEntity;
import microservice.shared_data.entities.GrupoSocialEntity;
import microservice.shared_data.entities.InstitucionEntity;
import microservice.shared_data.entities.UbigeoNombreEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;
import microservice.educalle.aliado.model.Aliado;
import microservice.educalle.gruposocial.model.GrupoSocial;
import microservice.educalle.institucion.model.Institucion;
import microservice.educalle.ubigeo.model.UbigeoNombre;

@Mapper(config = BaseMapStructConfig.class)
public interface AliadoEntityMapper {

      // * Dep´s

      // * Entity-To-Model
      @Mappings({
                  @Mapping(target = "zonaIntervencion", ignore = true),
      })
      Aliado toModel(AliadoEntity entity);

      // * Model-To-Entity
      AliadoEntity toEntity(Aliado model);

      @Mappings({
                  @Mapping(source = "ubigeo", target = "ubigeo", qualifiedByName = "mapIdUbigeoToEntity"),
                  @Mapping(source = "institucion", target = "institucion", qualifiedByName = "mapInstitucionToEntity"),
                  @Mapping(source = "grupoSocial", target = "grupoSocial", qualifiedByName = "mapGrupoSocialToEntity"),
      })
      void fromModelToEntity(Aliado source, @MappingTarget AliadoEntity target);

      // * Default method's
      @Named("mapIdUbigeoToEntity")
      default UbigeoNombreEntity mapIdUbigeoToEntity(UbigeoNombre ubigeo) {
            return ubigeo != null ? UbigeoNombreEntity.builder().idUbigeo(ubigeo.getIdUbigeo()).build() : null;
      }

      @Named("mapInstitucionToEntity")
      default InstitucionEntity mapInstitucionToEntity(Institucion institucion) {
            return institucion != null
                        ? InstitucionEntity.builder().idInstitucion(institucion.getIdInstitucion()).build()
                        : null;
      }

      @Named("mapGrupoSocialToEntity")
      default GrupoSocialEntity mapGrupoSocialToEntity(GrupoSocial grupoSocial) {
            return grupoSocial != null
                        ? GrupoSocialEntity.builder().idGrupoSocial(grupoSocial.getIdGrupoSocial()).build()
                        : null;
      }

}
