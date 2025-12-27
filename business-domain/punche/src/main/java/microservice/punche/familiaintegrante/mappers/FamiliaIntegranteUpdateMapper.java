package microservice.punche.familiaintegrante.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import microservice.punche.familiaintegrante.dtos.UpdateFamiliaIntegranteRequest;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface FamiliaIntegranteUpdateMapper {

   FamiliaIntegrante toModel(UpdateFamiliaIntegranteRequest create);

   List<FamiliaIntegrante> toModels(List<UpdateFamiliaIntegranteRequest> updates);

}
