package microservice.sigesu.equipotrabajo.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import microservice.sigesu.equipotrabajo.dtos.EquipoTrabajoSaveDto;
import microservice.sigesu.equipotrabajo.dtos.EquipoTrabajoUpdateDto;
import microservice.shared_data.entities.EquipoTrabajoEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface EquipoTrabajoMapper {

   void fromSaveDtoToEntity(EquipoTrabajoSaveDto dto, @MappingTarget EquipoTrabajoEntity entity);

   void fromUpdateDtoToEntity(EquipoTrabajoUpdateDto dto, @MappingTarget EquipoTrabajoEntity entity);

}
