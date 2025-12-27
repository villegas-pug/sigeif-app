package microservice.cedif.infrastructure.adapters.out.persistences.anexorespuesta;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.cedif.domain.models.AnexoPregunta;
import microservice.cedif.domain.models.AnexoRespuesta;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.models.Personal;
import microservice.cedif.domain.models.PotencialFamilia;
import microservice.shared_data.entities.AnexoPregutasEntity;
import microservice.shared_data.entities.AnexoRespuestaEntity;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoRespuestaEntityMapper {

   @Mappings({
         @Mapping(target = "familia", source = "familia", qualifiedByName = "mapFamiliaToFamiliaEntity"),
         @Mapping(target = "pregunta", source = "pregunta", qualifiedByName = "mapPreguntaToPreguntaEntity"),
         @Mapping(target = "integrante", source = "integrante", qualifiedByName = "mapIntegranteToIntegranteEntity"),
         @Mapping(target = "personal", source = "personal", qualifiedByName = "mapPersonalToPersonalEntity")
   })
   AnexoRespuestaEntity toEntity(AnexoRespuesta model);

   @Mappings({
         @Mapping(target = "personal", source = "personal", qualifiedByName = "mapPersonalToPersonalEntity")
   })
   void toEntity(AnexoRespuesta model, @MappingTarget AnexoRespuestaEntity entity);

   @Mappings({
         @Mapping(target = "familia", ignore = true),
         @Mapping(target = "integrante", ignore = true),
   })
   AnexoRespuesta toModel(AnexoRespuestaEntity entity);

   // * Deps Methods
   @Named("mapFamiliaToFamiliaEntity")
   default PotencialFamiliaEntity familiaToFamiliaEntity(PotencialFamilia familia) {
      return familia != null ? PotencialFamiliaEntity.builder().idFamilia(familia.getIdFamilia()).build() : null;
   }

   @Named("mapPreguntaToPreguntaEntity")
   default AnexoPregutasEntity preguntaToPreguntaEntity(AnexoPregunta pregunta) {
      return pregunta != null ? AnexoPregutasEntity.builder().idPregunta(pregunta.getIdPregunta()).build() : null;
   }

   @Named("mapIntegranteToIntegranteEntity")
   default IntegranteFamiliaEntity integranteToIntegranteEntity(FamiliaIntegrante integrante) {
      return integrante != null ? IntegranteFamiliaEntity.builder().idIntegrante(integrante.getIdIntegrante()).build()
            : null;
   }

   @Named("mapPersonalToPersonalEntity")
   default PersonalEntity mapPersonalToPersonalEntity(Personal personal) {
      return personal != null ? PersonalEntity.builder().idPersonal(personal.getIdPersonal()).build() : null;
   }

}
