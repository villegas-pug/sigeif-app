package microservice.cedif.infrastructure.adapters.out.persistences.servicio;

import java.util.List;
import org.mapstruct.Mapper;
import microservice.cedif.domain.models.Servicio;
import microservice.shared_data.entities.ServicioEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ServicioEntityMapper {

   Servicio toModel(ServicioEntity entity);

   List<Servicio> toModels(List<ServicioEntity> entities);

}
