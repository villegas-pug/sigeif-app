package microservice.punche.aliado.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.punche.aliado.dtos.CreateAliadoRequest;
import microservice.punche.aliado.model.Aliado;
import microservice.shared_data.mappers.BaseMapStructConfig;

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
