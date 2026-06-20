package microservice.educalle.potencialfamilia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.educalle.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.educalle.anexorespuesta.model.AnexoRespuesta;
import microservice.educalle.potencialfamilia.dtos.CreatePotencialFamiliaRequest;
import microservice.educalle.potencialfamilia.dtos.UpdatePotencialFamiliaRequest;
import microservice.educalle.potencialfamilia.model.PotencialFamilia;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PotencialFamiliaMapper {

      // * Dep's
      @Mappings({
                  @Mapping(source = "idPregunta", target = "pregunta.idPregunta")
      })
      AnexoRespuesta fromCreateRequestToModel(CreateAnexoRespuestaRequest request);

      /*
       * zonaIntervencion
       * aliado
       * servicio
       */

      @Mappings({
                  @Mapping(source = "idZona", target = "zonaIntervencion.idZona"),
                  @Mapping(source = "idAliado", target = "aliado.idAliado"),
                  @Mapping(source = "idServicio", target = "servicio.idServicio")
      })
      PotencialFamilia fromCreateRequestToModel(CreatePotencialFamiliaRequest request);

      @Mappings({
                  @Mapping(source = "idZona", target = "zonaIntervencion.idZona"),
                  @Mapping(source = "idAliado", target = "aliado.idAliado"),
                  @Mapping(source = "idServicio", target = "servicio.idServicio")
      })
      PotencialFamilia toModel(UpdatePotencialFamiliaRequest request);

}
