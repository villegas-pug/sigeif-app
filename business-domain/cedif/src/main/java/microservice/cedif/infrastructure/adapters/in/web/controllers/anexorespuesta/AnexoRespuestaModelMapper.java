package microservice.cedif.infrastructure.adapters.in.web.controllers.anexorespuesta;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.cedif.domain.models.AnexoRespuesta;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.models.Personal;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoRespuestaModelMapper {

      @Mappings({
                  @Mapping(target = "personal", source = "idPersonal", qualifiedByName = "mapIdToPersonalModel")
      })
      AnexoRespuesta fromUpdate(AnexoRespuestaUpdateRequest update);

      @Mappings({
                  @Mapping(target = "familia.idFamilia", source = "idFamilia"),
                  @Mapping(target = "pregunta.idPregunta", source = "idPregunta"),
                  @Mapping(target = "integrante", source = "idIntegrante", qualifiedByName = "mapIdToIntegranteModel"),
                  @Mapping(target = "personal", source = "idPersonal", qualifiedByName = "mapIdToPersonalModel")
      })
      AnexoRespuesta toModel(AnexoRespuestaCreateRequest create);

      List<AnexoRespuesta> toModels(List<AnexoRespuestaCreateRequest> creates);

      List<AnexoRespuesta> fromUpdates(List<AnexoRespuestaUpdateRequest> updates);

      // * Method's:

      @Named("mapIdToIntegranteModel")
      default FamiliaIntegrante idToIntegranteModel(Long idIntegrante) {
            return idIntegrante != null ? FamiliaIntegrante.builder().idIntegrante(idIntegrante).build() : null;
      }

      @Named("mapIdToPersonalModel")
      default Personal mapIdToPersonalModel(Long idPersonal) {
            return idPersonal != null ? Personal.builder().idPersonal(idPersonal).build() : null;
      }

}
