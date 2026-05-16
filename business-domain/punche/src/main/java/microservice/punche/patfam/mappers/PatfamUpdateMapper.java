package microservice.punche.patfam.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import microservice.punche.objetivoespecifico.models.Modulo;
import microservice.punche.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.punche.objetivoespecifico.models.Tema;
import microservice.punche.objetivoespecifico.models.Unidad;
import microservice.punche.patfam.dtos.CreateDetPatfamRequest;
import microservice.punche.patfam.dtos.UpdatePatfamRequest;
import microservice.punche.patfam.models.DetPatfam;
import microservice.punche.patfam.models.Patfam;
import microservice.punche.taller.model.Taller;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PatfamUpdateMapper {

   // * Dep´s
   @Mappings({
         @Mapping(source = "idObjetivo", target = "objetivo", qualifiedByName = "mapIdToObjetivo"),
         @Mapping(source = "idModulo", target = "modulo", qualifiedByName = "mapIdToModulo"),
         @Mapping(source = "idUnidad", target = "unidad", qualifiedByName = "mapIdToUnidad"),
         @Mapping(source = "idTema", target = "tema", qualifiedByName = "mapIdToTema"),
         @Mapping(source = "idSesion", target = "sesion.idSesion"),
         @Mapping(source = "nombreSesion", target = "sesion.nombre"),
         @Mapping(source = "usuRegistra", target = "sesion.usuRegistra"),

         // ! Nuevo
         // @Mapping(source = "idTaller", target = "taller", qualifiedByName =
         // "mapIdToTaller"),
         @Mapping(source = "idTaller", target = "taller.idTaller"),
         @Mapping(source = "nombreTaller", target = "taller.nombre"),
         @Mapping(source = "usuRegistra", target = "taller.usuRegistra"),
   })
   DetPatfam toModel(CreateDetPatfamRequest source);

   // * Create-To-Model
   Patfam toModel(UpdatePatfamRequest source);

   // * Default method's
   @Named("mapIdToModulo")
   default Modulo mapIdToModulo(Integer idModulo) {
      return idModulo != null ? Modulo.builder().idModulo(idModulo).build() : null;
   }

   @Named("mapIdToTema")
   default Tema mapIdToTema(Integer idTema) {
      return idTema != null ? Tema.builder().idTema(idTema).build() : null;
   }

   @Named("mapIdToTaller")
   default Taller mapIdToTaller(Integer idTaller) {
      return idTaller != null ? Taller.builder().idTaller(idTaller).build() : null;
   }

   @Named("mapIdToObjetivo")
   default ObjetivoEspecifico mapIdToObjetivo(Integer idObjetivo) {
      return idObjetivo != null ? ObjetivoEspecifico.builder().idObjetivo(idObjetivo).build() : null;
   }

   @Named("mapIdToUnidad")
   default Unidad mapIdToUnidad(Integer idUnidad) {
      return idUnidad != null ? Unidad.builder().idUnidad(idUnidad).build() : null;
   }

}
