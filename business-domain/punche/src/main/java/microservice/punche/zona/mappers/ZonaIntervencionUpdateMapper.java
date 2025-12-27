package microservice.punche.zona.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import microservice.punche.institucion.model.Institucion;
import microservice.punche.unidadorganica.model.UnidadOrganica;
import microservice.punche.zona.dtos.UpdateZonaIntervencionRequest;
import microservice.punche.zona.model.ZonaIntervencion;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ZonaIntervencionUpdateMapper {

   @Mappings({
         @Mapping(target = "institucion", source = "idInstitucion", qualifiedByName = "mapIdToInstitucion"),
         @Mapping(target = "unidadOrg", source = "idUnidadorg", qualifiedByName = "mapIdToUnidadOrg")
   })
   ZonaIntervencion toModel(UpdateZonaIntervencionRequest source);

   // * Methods

   @Named("mapIdToInstitucion")
   default Institucion mapIdToInstitucion(Long idInstitucion) {
      return idInstitucion != null ? Institucion.builder().idInstitucion(idInstitucion).build() : null;
   }

   @Named("mapIdToUnidadOrg")
   default UnidadOrganica mapIdToUnidadOrg(Long idUnidadorg) {
      return idUnidadorg != null ? UnidadOrganica.builder().idUO(idUnidadorg).build() : null;
   }

}
