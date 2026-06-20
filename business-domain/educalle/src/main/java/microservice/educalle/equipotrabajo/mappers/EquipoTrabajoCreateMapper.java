package microservice.educalle.equipotrabajo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.educalle.equipotrabajo.dtos.CreateEquipoTrabajoRequest;
import microservice.educalle.equipotrabajo.model.EquipoTrabajo;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface EquipoTrabajoCreateMapper {

      @Mappings({
                  @Mapping(source = "idZona", target = "zonaIntervencion.idZona"),
                  @Mapping(source = "idPersonal", target = "personal.idPersonal"),
                  @Mapping(source = "telefono", target = "personal.persona.telefono", defaultValue = "-"),
                  @Mapping(source = "correo", target = "personal.persona.correo", defaultValue = "-"),
                  @Mapping(source = "idCargo", target = "cargo.idCargo")
      })
      EquipoTrabajo toModel(CreateEquipoTrabajoRequest source);

}
