package microservice.sigesu.unidadsesion.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.sigesu.objetivoespecifico.models.Unidad;
import microservice.sigesu.taller.model.Taller;
import microservice.sigesu.unidadsesion.dtos.CreateUnidadSesionRequest;
import microservice.sigesu.unidadsesion.model.UnidadSesion;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface UnidadSesionCreateMapper {

   // * Dep's
   Taller toModel(TallerEntity source);

   // * Entity-To-Entity
   @Mappings({
         @Mapping(target = "unidad", source = "idUnidad", qualifiedByName = "mapIdToUnidad")
   })
   UnidadSesion toModel(CreateUnidadSesionRequest source);

   // * Default method's
   @Named("mapIdToUnidad")
   default Unidad mapIdToUnidad(Integer idUnidad) {
      return idUnidad != null ? Unidad.builder().idUnidad(idUnidad).build() : null;
   }

}
