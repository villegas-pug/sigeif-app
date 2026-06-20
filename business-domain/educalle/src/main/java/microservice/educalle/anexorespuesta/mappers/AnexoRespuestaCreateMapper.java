package microservice.educalle.anexorespuesta.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import microservice.educalle.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.educalle.anexorespuesta.model.AnexoRespuesta;
import microservice.educalle.familiaintegrante.model.FamiliaIntegrante;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface AnexoRespuestaCreateMapper {

      @Mappings({
                  @Mapping(target = "familia.idFamilia", source = "idFamilia"),
                  @Mapping(target = "pregunta.idPregunta", source = "idPregunta"),
                  @Mapping(target = "integrante", source = "idIntegrante", qualifiedByName = "mapIdToIntegranteModel")
      })
      AnexoRespuesta toModel(CreateAnexoRespuestaRequest create);

      List<AnexoRespuesta> toModels(List<CreateAnexoRespuestaRequest> creates);

      // * Default methods
      @Named("mapIdToIntegranteModel")
      default FamiliaIntegrante idToIntegranteModel(Long idIntegrante) {
            return idIntegrante != null ? FamiliaIntegrante.builder().idIntegrante(idIntegrante).build() : null;
      }
}
