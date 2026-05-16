package microservice.sigesu.aliado.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.shared_data.mappers.BaseMapStructConfig;
import microservice.sigesu.aliado.dtos.CreateAliadoRequest;
import microservice.sigesu.aliado.model.Aliado;

@Mapper(config = BaseMapStructConfig.class)
public interface AliadoCreateMapper {

      @Mappings({
                  @Mapping(source = "idZona", target = "zonaIntervencion.idZona"),
                  @Mapping(source = "idInstitucion", target = "institucion.idInstitucion"),
                  @Mapping(source = "idGrupoSocial", target = "grupoSocial.idGrupoSocial"),
                  @Mapping(source = "idUbigeo", target = "ubigeo.idUbigeo")
      })
      Aliado toModel(CreateAliadoRequest source);

}
