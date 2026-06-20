package microservice.educalle.familiaintegrante.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import microservice.educalle.familiaintegrante.dtos.UpdateFamiliaIntegranteRequest;
import microservice.educalle.familiaintegrante.model.FamiliaIntegrante;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface FamiliaIntegranteUpdateMapper {

   FamiliaIntegrante toModel(UpdateFamiliaIntegranteRequest create);

   List<FamiliaIntegrante> toModels(List<UpdateFamiliaIntegranteRequest> updates);

}
