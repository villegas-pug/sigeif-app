package microservice.punche.patfam.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.punche.ejecucionsesion.model.EjecucionSesion;
import microservice.punche.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;
import microservice.punche.objetivoespecifico.models.Modulo;
import microservice.punche.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.punche.objetivoespecifico.models.Unidad;
import microservice.punche.patfam.models.DetPatfam;
import microservice.punche.patfam.models.Patfam;
import microservice.punche.unidadsesion.model.UnidadSesion;
import microservice.shared_data.entities.DetPatfamEntity;
import microservice.shared_data.entities.EjecucionSesionEntity;
import microservice.shared_data.entities.EjecucionSesionIntegranteEntity;
import microservice.shared_data.entities.ModuloEntity;
import microservice.shared_data.entities.ObjetivoEspecificoEntity;
import microservice.shared_data.entities.PatfamEntity;
import microservice.shared_data.entities.UnidadEntity;
import microservice.shared_data.entities.UnidadSesionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PatfamEntityMapper {

      // * Dep's

      @Mappings({ // ! Evita recursión en el mapeo de `DetPatfam`.
                  @Mapping(target = "servicio", ignore = true),
                  @Mapping(target = "modulos", ignore = true),
                  @Mapping(target = "talleres", ignore = true),
                  @Mapping(target = "sesiones", ignore = true),
      })
      ObjetivoEspecifico toModel(ObjetivoEspecificoEntity source);

      @Mappings({ // ! Evita recursión en el mapeo de `DetPatfam`.
                  @Mapping(target = "unidades", ignore = true),
                  @Mapping(target = "sesiones", ignore = true),
                  @Mapping(target = "talleres", ignore = true),
      })
      Modulo toModel(ModuloEntity source);

      @Mappings({ // ! Evita recursión en el mapeo de `DetPatfam`.
                  @Mapping(target = "temas", ignore = true),
                  @Mapping(target = "sesiones", ignore = true),
      })
      Unidad toModel(UnidadEntity source);

      @Mappings({ // ! Evita recursión en el mapeo de `DetPatfam`.
                  @Mapping(target = "talleres", ignore = true),
                  @Mapping(target = "unidad", ignore = true),
                  @Mapping(target = "modulo", ignore = true),
                  @Mapping(target = "objetivo", ignore = true),
      })
      UnidadSesion toModel(UnidadSesionEntity source);

      @Mappings({
                  @Mapping(target = "ejecucionSesion", ignore = true),
                  @Mapping(target = "familia.integranteFamilia", ignore = true)
      })
      EjecucionSesionIntegrante toModel(EjecucionSesionIntegranteEntity source);

      @Mappings({
                  @Mapping(target = "detPatfam", ignore = true),
                  @Mapping(target = "sesion.talleres", ignore = true)
      })
      EjecucionSesion toModel(EjecucionSesionEntity source);

      @Mappings({
                  @Mapping(target = "patfam", ignore = true),
                  @Mapping(target = "ejecucionSesiones", ignore = true),
                  @Mapping(target = "taller.sesion", ignore = true)
      })
      DetPatfam toModel(DetPatfamEntity source);

      // * Model-To-Entity
      void fromModelToEntity(Patfam source, @MappingTarget PatfamEntity target);

      PatfamEntity toEntity(Patfam source);

      // * Entity-To-Model
      @Mappings({
                  @Mapping(target = "familia", ignore = true)
      })
      Patfam toModel(PatfamEntity source);

      // * Default method's

}
