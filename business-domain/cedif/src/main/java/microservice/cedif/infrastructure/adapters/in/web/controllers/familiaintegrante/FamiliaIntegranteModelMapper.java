package microservice.cedif.infrastructure.adapters.in.web.controllers.familiaintegrante;

import java.util.List;
import org.mapstruct.Mapper;
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface FamiliaIntegranteModelMapper {

   FamiliaIntegrante fromCreateToModel(FamiliaIntegranteCreateRequest create);

   FamiliaIntegrante fromUpdateToModel(FamiliaIntegranteUpdateRequest update);

   List<FamiliaIntegrante> fromUpdatesToModels(List<FamiliaIntegranteUpdateRequest> updates);

   List<FamiliaIntegrante> fromCreatesToModels(List<FamiliaIntegranteCreateRequest> creates);

   List<FamiliaIntegranteResponse> toResponse(List<FamiliaIntegrante> models);

}
