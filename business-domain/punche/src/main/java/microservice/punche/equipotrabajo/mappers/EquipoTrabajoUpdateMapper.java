package microservice.punche.equipotrabajo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.punche.equipotrabajo.dtos.UpdateEquipoTrabajoRequest;
import microservice.punche.equipotrabajo.model.EquipoTrabajo;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface EquipoTrabajoUpdateMapper {

      @Mappings({
                  @Mapping(source = "idPersonal", target = "personal.idPersonal"),
                  @Mapping(source = "telefono", target = "personal.persona.telefono", defaultValue = "-"),
                  @Mapping(source = "correo", target = "personal.persona.correo", defaultValue = "-"),
                  @Mapping(source = "idCargo", target = "cargo.idCargo")
      })
      EquipoTrabajo toModel(UpdateEquipoTrabajoRequest source);

}
