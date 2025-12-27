package microservice.cedif.infrastructure.adapters.out.persistences.pais;

import java.util.List;

import org.mapstruct.Mapper;
import microservice.cedif.domain.models.Pais;
import microservice.shared_data.entities.PaisEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PaisEntityMapper {

   Pais toModel(PaisEntity source);

   List<Pais> toModels(List<PaisEntity> sources);

}
