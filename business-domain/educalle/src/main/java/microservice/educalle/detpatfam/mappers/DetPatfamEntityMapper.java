package microservice.educalle.detpatfam.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import microservice.educalle.ejecucionsesion.model.EjecucionSesion;
import microservice.educalle.ejecucionsesionintegrante.model.EjecucionSesionIntegrante;
import microservice.educalle.familiaintegrante.model.FamiliaIntegrante;
import microservice.educalle.objetivoespecifico.models.Modulo;
import microservice.educalle.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.educalle.objetivoespecifico.models.Tema;
import microservice.educalle.objetivoespecifico.models.Unidad;
import microservice.educalle.patfam.models.DetPatfam;
import microservice.educalle.patfam.models.Patfam;
import microservice.educalle.potencialfamilia.model.PotencialFamilia;
import microservice.educalle.taller.model.Taller;
import microservice.educalle.unidadsesion.model.UnidadSesion;
import microservice.shared_data.entities.DetPatfamEntity;
import microservice.shared_data.entities.EjecucionSesionEntity;
import microservice.shared_data.entities.EjecucionSesionIntegranteEntity;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.entities.ModuloEntity;
import microservice.shared_data.entities.ObjetivoEspecificoEntity;
import microservice.shared_data.entities.PatfamEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.entities.TemaEntity;
import microservice.shared_data.entities.UnidadEntity;
import microservice.shared_data.entities.UnidadSesionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface DetPatfamEntityMapper {

      // * Dep's

      @Mappings({ // ! Evita recursión en el mapeo de `DetPatfam`.
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
                  @Mapping(target = "sesiones", ignore = true)
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
                  @Mapping(target = "integranteFamilia.familia", ignore = true),
                  @Mapping(target = "integranteFamilia.anexosRespuestas", ignore = true)
      })
      EjecucionSesionIntegrante toModel(EjecucionSesionIntegranteEntity source);

      @Mappings({
                  @Mapping(target = "detPatfam", ignore = true),
                  @Mapping(target = "sesion", ignore = true)
      })
      EjecucionSesion toModel(EjecucionSesionEntity source);

      @Mappings({
                  @Mapping(target = "familia", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true)
      })
      FamiliaIntegrante toModel(IntegranteFamiliaEntity source);

      @Mappings({
                  @Mapping(target = "zonaIntervencion", ignore = true),
                  @Mapping(target = "aliado", ignore = true),
                  @Mapping(target = "servicio", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true),
                  @Mapping(target = "motivosReferencia", ignore = true)
      })
      PotencialFamilia toModel(PotencialFamiliaEntity source);

      @Mappings({
                  @Mapping(target = "detPatfam", ignore = true),
      })
      Patfam toModel(PatfamEntity source);

      // * Entity-To-Model
      @Mappings({
                  @Mapping(target = "taller.sesion", ignore = true),
      })
      DetPatfam toModel(DetPatfamEntity source);

      // * Model-To-Entity
      DetPatfamEntity toEntity(DetPatfam source);

      @Mappings({
                  @Mapping(source = "objetivo", target = "objetivo", qualifiedByName = "mapIdToObjetivo"),
                  @Mapping(source = "modulo", target = "modulo", qualifiedByName = "mapIdToModulo"),
                  @Mapping(source = "unidad", target = "unidad", qualifiedByName = "mapIdToUnidad"),
                  @Mapping(source = "tema", target = "tema", qualifiedByName = "mapIdToTema"),
                  @Mapping(source = "sesion", target = "sesion", qualifiedByName = "mapIdToSesion"),
                  @Mapping(source = "taller", target = "taller", qualifiedByName = "mapIdToTaller"),
      })
      void fromModelToEntity(DetPatfam source, @MappingTarget DetPatfamEntity target);

      // * Default method's
      @Named("mapIdToModulo")
      default ModuloEntity mapIdToModulo(Modulo modulo) {
            return modulo != null ? ModuloEntity.builder().idModulo(modulo.getIdModulo()).build() : null;
      }

      @Named("mapIdToTema")
      default TemaEntity mapIdToTema(Tema tema) {
            return tema != null ? TemaEntity.builder().idTema(tema.getIdTema()).build() : null;
      }

      @Named("mapIdToTaller")
      default TallerEntity mapIdToTaller(Taller taller) {
            return taller != null ? TallerEntity.builder().idTaller(taller.getIdTaller()).build() : null;
      }

      @Named("mapIdToObjetivo")
      default ObjetivoEspecificoEntity mapIdToObjetivo(ObjetivoEspecifico objetivo) {
            return objetivo != null ? ObjetivoEspecificoEntity.builder().idObjetivo(objetivo.getIdObjetivo()).build()
                        : null;
      }

      @Named("mapIdToUnidad")
      default UnidadEntity mapIdToUnidad(Unidad unidad) {
            return unidad != null ? UnidadEntity.builder().idUnidad(unidad.getIdUnidad()).build() : null;
      }

      @Named("mapIdToSesion")
      default UnidadSesionEntity mapIdToSesion(UnidadSesion sesion) {
            return sesion != null ? UnidadSesionEntity.builder().idSesion(sesion.getIdSesion()).build() : null;
      }

}
