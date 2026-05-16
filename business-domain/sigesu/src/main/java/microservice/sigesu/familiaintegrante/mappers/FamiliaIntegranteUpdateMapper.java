package microservice.sigesu.familiaintegrante.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import microservice.sigesu.familiaintegrante.dtos.UpdateFamiliaIntegranteRequest;
import microservice.sigesu.familiaintegrante.model.FamiliaIntegrante;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface FamiliaIntegranteUpdateMapper {

   FamiliaIntegrante toModel(UpdateFamiliaIntegranteRequest create);

   List<FamiliaIntegrante> toModels(List<UpdateFamiliaIntegranteRequest> updates);

}
