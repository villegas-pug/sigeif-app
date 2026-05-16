package microservice.sigesu.familiaintegrante.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import microservice.sigesu.familiaintegrante.dtos.CreateFamiliaIntegranteRequest;
import microservice.sigesu.familiaintegrante.model.FamiliaIntegrante;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface FamiliaIntegranteCreateMapper {

   FamiliaIntegrante toModel(CreateFamiliaIntegranteRequest update);

   List<FamiliaIntegrante> toModels(List<CreateFamiliaIntegranteRequest> creates);

}
