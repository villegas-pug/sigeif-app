package microservice.cedif.infrastructure.adapters.in.web.controllers.potencialfamilia;

import org.mapstruct.Mapper;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.models.MotivoReferecia;
import microservice.cedif.domain.ports.in.anexorespuesta.AnexoRespuestaCreateCommand;
import microservice.cedif.domain.ports.in.potencialfamilia.PotencialFamiliaCreateCommand;
import microservice.cedif.infrastructure.adapters.in.web.controllers.anexorespuesta.AnexoRespuestaCreateRequest;
import microservice.cedif.infrastructure.adapters.in.web.controllers.familiaintegrante.FamiliaIntegranteCreateRequest;
import microservice.cedif.infrastructure.adapters.in.web.controllers.motivoreferencia.MotivoRefereciaCreateRequest;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PotenciaFamiliaCommandMapper {

   // * Para mapeo interno: toCreate()
   FamiliaIntegrante fromFamiliaIntegranteCreateRequestToModel(FamiliaIntegranteCreateRequest request);

   MotivoReferecia fromMotivoRefereciaCreateRequestToModel(MotivoRefereciaCreateRequest request);

   AnexoRespuestaCreateCommand fromAnexoRespuestaCreateRequestToCommand(AnexoRespuestaCreateRequest request);

   // * Para Mapeo externo:
   PotencialFamiliaCreateCommand toCreate(PotencialFamiliaCreateRequest request);

}
