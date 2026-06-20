package microservice.educalle.centroreferencia.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.educalle.centroreferencia.dtos.UnidadOrganicaResponse;
import microservice.shared_data.entities.UnidadOrganicaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface UnidadOrganicaResponseMapper {

   @Mappings({
         @Mapping(target = "representante", source = "representante.persona"),
   })
   UnidadOrganicaResponse fromModel(UnidadOrganicaEntity entity);

   List<UnidadOrganicaResponse> fromModels(List<UnidadOrganicaEntity> entities);

}
