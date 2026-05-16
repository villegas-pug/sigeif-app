package microservice.punche.unidadtema.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.punche.objetivoespecifico.models.Unidad;
import microservice.punche.taller.model.Taller;
import microservice.punche.unidadtema.dtos.CreateUnidadTemaRequest;
import microservice.punche.unidadtema.model.UnidadTema;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface UnidadTemaCreateMapper {

   // * Dep's
   Taller toModel(TallerEntity source);

   // * Entity-To-Entity
   @Mappings({
         @Mapping(target = "unidad", source = "idUnidad", qualifiedByName = "mapIdToUnidad")
   })
   UnidadTema toModel(CreateUnidadTemaRequest source);

   // * Default method's
   @Named("mapIdToUnidad")
   default Unidad mapIdToUnidad(Integer idUnidad) {
      return idUnidad != null ? Unidad.builder().idUnidad(idUnidad).build() : null;
   }

}
