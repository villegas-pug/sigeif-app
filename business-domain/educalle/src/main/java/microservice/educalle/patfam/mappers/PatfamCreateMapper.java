package microservice.educalle.patfam.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.educalle.objetivoespecifico.models.Modulo;
import microservice.educalle.objetivoespecifico.models.Tema;
import microservice.educalle.objetivoespecifico.models.Unidad;
import microservice.educalle.patfam.dtos.CreateDetPatfamRequest;
import microservice.educalle.patfam.dtos.CreatePatfamRequest;
import microservice.educalle.patfam.models.DetPatfam;
import microservice.educalle.patfam.models.Patfam;
import microservice.educalle.taller.model.Taller;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PatfamCreateMapper {

   // * Dep´s
   @Mappings({
         @Mapping(source = "idObjetivo", target = "objetivo.idObjetivo"),
         @Mapping(source = "idModulo", target = "modulo", qualifiedByName = "mapIdToModulo"),
         @Mapping(source = "idUnidad", target = "unidad", qualifiedByName = "mapIdToUnidad"),
         @Mapping(source = "idTema", target = "tema", qualifiedByName = "mapIdToTema"),
         @Mapping(source = "idSesion", target = "sesion.idSesion"),
         @Mapping(source = "nombreSesion", target = "sesion.nombre"),
         @Mapping(source = "usuRegistra", target = "sesion.usuRegistra"),
         @Mapping(source = "idTaller", target = "taller", qualifiedByName = "mapIdToTaller"),
   })
   DetPatfam toModel(CreateDetPatfamRequest source);

   // * Create-To-Model
   @Mappings({
         @Mapping(source = "idFamilia", target = "familia.idFamilia")
   })
   Patfam toModel(CreatePatfamRequest source);

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

   @Named("mapIdToUnidad")
   default Unidad mapIdToUnidad(Integer idUnidad) {
      return idUnidad != null ? Unidad.builder().idUnidad(idUnidad).build() : null;
   }

}
