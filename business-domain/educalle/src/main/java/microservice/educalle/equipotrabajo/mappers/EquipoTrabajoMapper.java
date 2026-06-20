package microservice.educalle.equipotrabajo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import microservice.educalle.equipotrabajo.dtos.EquipoTrabajoSaveDto;
import microservice.educalle.equipotrabajo.dtos.EquipoTrabajoUpdateDto;
import microservice.shared_data.entities.EquipoTrabajoEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface EquipoTrabajoMapper {

   void fromSaveDtoToEntity(EquipoTrabajoSaveDto dto, @MappingTarget EquipoTrabajoEntity entity);

   void fromUpdateDtoToEntity(EquipoTrabajoUpdateDto dto, @MappingTarget EquipoTrabajoEntity entity);

}
