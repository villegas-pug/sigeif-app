package microservice.sigesu.potencialfamilia.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.sigesu.anexorespuesta.dtos.CreateAnexoRespuestaRequest;
import microservice.sigesu.anexorespuesta.model.AnexoRespuesta;
import microservice.sigesu.potencialfamilia.dtos.CreatePotencialFamiliaRequest;
import microservice.sigesu.potencialfamilia.dtos.UpdatePotencialFamiliaRequest;
import microservice.sigesu.potencialfamilia.model.PotencialFamilia;
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
