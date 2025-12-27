package microservice.punche.taller.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import microservice.punche.taller.dtos.CreateTallerRequest;
import microservice.punche.taller.model.Taller;
import microservice.punche.unidadsesion.model.UnidadSesion;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface TallerCreateMapper {

   // * Model-To-Entity
   @Mappings({
         @Mapping(source = "idSesion", target = "sesion", qualifiedByName = "mapIdToSesion")
   })
   Taller toModel(CreateTallerRequest source);

   // * Default method's
   @Named("mapIdToSesion")
   default UnidadSesion mapIdToSesion(Integer idSesion) {
      return idSesion != null ? UnidadSesion.builder().idSesion(idSesion).build() : null;
   }
}
