package microservice.sigesu.aliado.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.shared_data.mappers.BaseMapStructConfig;
import microservice.sigesu.aliado.dtos.UpdateAliadoRequest;
import microservice.sigesu.aliado.model.Aliado;

@Mapper(config = BaseMapStructConfig.class)
public interface AliadoUpdateMapper {

      // * Dep´s

      // * Create-To-Model
      @Mappings({
                  @Mapping(source = "idInstitucion", target = "institucion.idInstitucion"),
                  @Mapping(source = "idGrupoSocial", target = "grupoSocial.idGrupoSocial"),
                  @Mapping(source = "idUbigeo", target = "ubigeo.idUbigeo")
      })
      Aliado toModel(UpdateAliadoRequest source);

      // * Default method's

}
