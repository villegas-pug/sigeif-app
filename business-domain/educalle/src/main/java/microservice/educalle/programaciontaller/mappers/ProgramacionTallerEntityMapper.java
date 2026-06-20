package microservice.educalle.programaciontaller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import microservice.educalle.familiaintegrante.model.FamiliaIntegrante;
import microservice.educalle.objetivoespecifico.models.Modulo;
import microservice.educalle.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.educalle.patfam.models.DetPatfam;
import microservice.educalle.programaciontaller.model.ProgramacionTaller;
import microservice.educalle.programaciontallerfamilia.model.ProgramacionTallerFamilia;
import microservice.educalle.taller.model.Taller;
import microservice.educalle.unidadsesion.model.UnidadSesion;
import microservice.shared_data.entities.DetPatfamEntity;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.entities.ModuloEntity;
import microservice.shared_data.entities.ObjetivoEspecificoEntity;
import microservice.shared_data.entities.ProgramacionTallerEntity;
import microservice.shared_data.entities.ProgramacionTallerFamiliaEntity;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.entities.UnidadSesionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ProgramacionTallerEntityMapper {

      // * Dep´s

      @Mappings({
                  @Mapping(target = "servicio", ignore = true),
                  @Mapping(target = "modulos", ignore = true),
                  @Mapping(target = "talleres", ignore = true),
      })
      ObjetivoEspecifico toModel(ObjetivoEspecificoEntity source);

      @Mappings({
                  @Mapping(target = "unidades", ignore = true),
                  @Mapping(target = "sesiones", ignore = true),
                  @Mapping(target = "talleres", ignore = true),
      })
      Modulo toModel(ModuloEntity source);

      @Mappings({
                  @Mapping(target = "talleres", ignore = true),
                  @Mapping(target = "unidad", ignore = true),
                  @Mapping(target = "modulo", ignore = true),
                  @Mapping(target = "objetivo", ignore = true),
      })
      UnidadSesion toModel(UnidadSesionEntity source);

      @Mappings({
                  @Mapping(target = "patfam", ignore = true),
                  @Mapping(target = "ejecucionSesiones", ignore = true),
                  @Mapping(target = "unidad.temas", ignore = true),
                  @Mapping(target = "unidad.sesiones", ignore = true),
                  @Mapping(target = "taller.sesion", ignore = true),
      })
      DetPatfam toModel(DetPatfamEntity source);

      @Mappings({
                  @Mapping(target = "familia", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true)
      })
      FamiliaIntegrante toModel(IntegranteFamiliaEntity source);

      @Mappings({
                  @Mapping(target = "progTaller", ignore = true),
                  @Mapping(target = "familia.zonaIntervencion", ignore = true),
                  @Mapping(target = "familia.aliado", ignore = true),
                  @Mapping(target = "familia.anexosRespuestas", ignore = true),
                  @Mapping(target = "familia.motivosReferencia", ignore = true),
      })
      ProgramacionTallerFamilia toModel(ProgramacionTallerFamiliaEntity source);

      // * Model-To-Entity
      @Mapping(target = "progTaller", ignore = true)
      void fromModelToEntity(ProgramacionTallerFamilia source, @MappingTarget ProgramacionTallerFamiliaEntity target);

      @Mappings({
                  @Mapping(source = "taller", target = "taller", qualifiedByName = "mapTallerToEntity"),
      })
      void fromModelToEntity(ProgramacionTaller source, @MappingTarget ProgramacionTallerEntity target);

      @Mapping(target = "progTaller", ignore = true)
      ProgramacionTallerFamiliaEntity toEntity(ProgramacionTallerFamilia source);

      ProgramacionTallerEntity toEntity(ProgramacionTaller source);

      @Mappings({
                  @Mapping(target = "objetivoEspecifico", ignore = true),
                  @Mapping(target = "modulo", ignore = true),
                  @Mapping(target = "sesion", ignore = true),
      })
      Taller toModel(TallerEntity source);

      // * Entity-To-Model
      @Mappings({
                  @Mapping(target = "unidadorg.potencialesFamilias", ignore = true),
      })
      ProgramacionTaller toModel(ProgramacionTallerEntity source);

      // * Default method's
      @Named("mapTallerToEntity")
      default TallerEntity mapTallerToEntity(Taller taller) {
            return taller.getIdTaller() == null
                        ? TallerEntity.builder()
                                    .nombre(taller.getNombre())
                                    .modulo( // ! Cedif
                                                taller.getModulo() != null
                                                            ? ModuloEntity
                                                                        .builder()
                                                                        .idModulo(taller.getModulo().getIdModulo())
                                                                        .build()
                                                            : null

                                    )
                                    .sesion( // ! PUnche
                                                taller.getSesion() != null
                                                            ? UnidadSesionEntity
                                                                        .builder()
                                                                        .idSesion(taller.getSesion().getIdSesion())
                                                                        .build()
                                                            : null)
                                    .objetivoEspecifico( // ! Acercandonos
                                                taller.getObjetivoEspecifico() != null
                                                            ? ObjetivoEspecificoEntity
                                                                        .builder()
                                                                        .idObjetivo(taller.getObjetivoEspecifico()
                                                                                    .getIdObjetivo())
                                                                        .build()
                                                            : null)
                                    .usuRegistra(taller.getUsuRegistra())
                                    .build()
                        : TallerEntity.builder().idTaller(taller.getIdTaller()).build();
      }

}
