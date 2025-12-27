package microservice.punche.objetivoespecifico.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import microservice.punche.objetivoespecifico.models.Modulo;
import microservice.punche.objetivoespecifico.models.ObjetivoEspecifico;
import microservice.punche.objetivoespecifico.models.Tema;
import microservice.punche.objetivoespecifico.models.Unidad;
import microservice.punche.taller.model.Taller;
import microservice.punche.unidadsesion.model.UnidadSesion;
import microservice.shared_data.entities.ModuloEntity;
import microservice.shared_data.entities.ObjetivoEspecificoEntity;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.entities.TemaEntity;
import microservice.shared_data.entities.UnidadEntity;
import microservice.shared_data.entities.UnidadSesionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ObjetivoEspecificoEntityMapper {

      // * Dep's
      @Mappings({
                  @Mapping(target = "sesion", ignore = true),
                  @Mapping(target = "objetivoEspecifico", ignore = true),
                  @Mapping(target = "modulo", ignore = true),
      })
      Taller toModel(TallerEntity source);

      @Mappings({
                  @Mapping(target = "unidad", ignore = true),
                  @Mapping(target = "modulo", ignore = true),
                  @Mapping(target = "objetivo", ignore = true),
      })
      UnidadSesion toModel(UnidadSesionEntity source);

      Tema toModel(TemaEntity source);

      @Mappings({
                  // @Mapping(target = "temas", ignore = true),
      })
      Unidad toModel(UnidadEntity source);

      Modulo toModel(ModuloEntity source);

      // * Entity-To-Model
      ObjetivoEspecifico toModel(ObjetivoEspecificoEntity source);

      List<ObjetivoEspecifico> toModels(List<ObjetivoEspecificoEntity> sources);

}
