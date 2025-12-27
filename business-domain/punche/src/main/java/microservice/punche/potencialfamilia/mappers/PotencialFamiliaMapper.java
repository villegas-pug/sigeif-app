package microservice.punche.potencialfamilia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.punche.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.punche.potencialfamilia.dtos.CreatePotencialFamiliaRequest;
import microservice.punche.potencialfamilia.dtos.UpdatePotencialFamiliaRequest;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
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
