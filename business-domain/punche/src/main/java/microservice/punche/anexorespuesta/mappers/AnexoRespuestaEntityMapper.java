package microservice.punche.anexorespuesta.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.punche.anexopregunta.model.AnexoPregunta;
import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
import microservice.shared_data.entities.AnexoPregutasEntity;
import microservice.shared_data.entities.AnexoRespuestaEntity;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoRespuestaEntityMapper {

   @Mappings({
         @Mapping(target = "familia", source = "familia", qualifiedByName = "mapFamiliaToFamiliaEntity"),
         @Mapping(target = "pregunta", source = "pregunta", qualifiedByName = "mapPreguntaToPreguntaEntity"),
         @Mapping(target = "integrante", source = "integrante", qualifiedByName = "mapIntegranteToIntegranteEntity")
   })
   AnexoRespuestaEntity toEntity(AnexoRespuesta model);

   void toEntity(AnexoRespuesta model, @MappingTarget AnexoRespuestaEntity entity);

   @Mappings({
         @Mapping(target = "familia", ignore = true),
         @Mapping(target = "integrante", ignore = true)
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

}
