package microservice.sigesu.programaciontaller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.sigesu.objetivoespecifico.models.Modulo;
import microservice.sigesu.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.sigesu.objetivoespecifico.models.Unidad;
import microservice.sigesu.personal.model.Personal;
import microservice.sigesu.programaciontaller.dtos.CreateProgramacionTallerRequest;
import microservice.sigesu.programaciontaller.model.ProgramacionTaller;
import microservice.sigesu.programaciontallerfamilia.dtos.CreateProgramacionTallerFamiliaRequest;
import microservice.sigesu.programaciontallerfamilia.model.ProgramacionTallerFamilia;
import microservice.sigesu.unidadorganica.model.UnidadOrganica;
import microservice.sigesu.unidadsesion.model.UnidadSesion;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ProgramacionTallerCreateMapper {

      // * Dep´s
      @Mappings({
                  @Mapping(source = "idFamilia", target = "familia.idFamilia"),
      })
      ProgramacionTallerFamilia toModel(CreateProgramacionTallerFamiliaRequest source);

      // * Create-To-Model
      @Mappings({
                  @Mapping(source = "idModulo", target = "taller.modulo", qualifiedByName = "mapIdToModulo"), // !
                  // Cedif
                  @Mapping(source = "idSesion", target = "taller.sesion", qualifiedByName = "mapIdToSesion"), // !
                  // Punche
                  @Mapping(source = "idObjetivo", target = "taller.objetivoEspecifico", qualifiedByName = "mapIdToObjetivo"), // !
                  // Acercandonos

                  @Mapping(source = "idTaller", target = "taller.idTaller"), // * Si existe sesión
                  @Mapping(source = "nombreTaller", target = "taller.nombre"),
                  @Mapping(source = "usuRegistra", target = "taller.usuRegistra"),
                  @Mapping(source = "idPersonal", target = "personal", qualifiedByName = "mapIdToPersonal"),
                  @Mapping(source = "idUO", target = "unidadorg", qualifiedByName = "mapIdToUO"),
      })
      ProgramacionTaller toModel(CreateProgramacionTallerRequest source);

      // * Default method's
      @Named("mapIdToModulo")
      default Modulo mapIdToModulo(Integer idModulo) {
            return idModulo != null ? Modulo.builder().idModulo(idModulo).build() : null;
      }

      @Named("mapIdToSesion")
      default UnidadSesion mapIdToSesion(Integer idSesion) {
            return idSesion != null ? UnidadSesion.builder().idSesion(idSesion).build() : null;
      }

      @Named("mapIdToObjetivo")
      default ObjetivoEspecifico mapIdToObjetivo(Integer idObjetivo) {
            return idObjetivo != null ? ObjetivoEspecifico.builder().idObjetivo(idObjetivo).build() : null;
      }

      @Named("mapIdToPersonal")
      default Personal mapIdToPersonal(Long idPersonal) {
            return idPersonal != null ? Personal.builder().idPersonal(idPersonal).build() : null;
      }

      @Named("mapIdToUO")
      default UnidadOrganica mapIdToUO(Long idUO) {
            return idUO != null ? UnidadOrganica.builder().idUO(idUO).build() : null;
      }

}
