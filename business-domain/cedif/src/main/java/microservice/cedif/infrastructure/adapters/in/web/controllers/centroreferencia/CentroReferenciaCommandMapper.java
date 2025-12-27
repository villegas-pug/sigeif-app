package microservice.cedif.infrastructure.adapters.in.web.controllers.centroreferencia;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import microservice.cedif.domain.ports.in.centroreferencia.CentroReferenciaCreateCommand;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface CentroReferenciaCommandMapper {

   CentroReferenciaCreateCommand toCreate(CentroReferenciaCreateRequest request);

}
