package microservice.punche.equipotrabajo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.punche.equipotrabajo.dtos.EquipoTrabajoResponse;
import microservice.punche.equipotrabajo.model.EquipoTrabajo;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface EquipoTrabajoResponseMapper {

      @Mappings({
                  @Mapping(source = "personal.persona", target = "."),
                  @Mapping(source = "personal.idPersonal", target = "idPersonal")
      })
      EquipoTrabajoResponse toResponse(EquipoTrabajo source);

}
