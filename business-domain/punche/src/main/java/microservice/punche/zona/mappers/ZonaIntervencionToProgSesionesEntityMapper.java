package microservice.punche.zona.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import microservice.punche.aliado.model.Aliado;
import microservice.punche.equipotrabajo.model.EquipoTrabajo;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
import microservice.punche.unidadorganica.model.UnidadOrganica;
import microservice.punche.zona.model.ZonaIntervencion;
import microservice.shared_data.entities.AliadoEntity;
import microservice.shared_data.entities.EquipoTrabajoEntity;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.entities.UnidadOrganicaEntity;
import microservice.shared_data.entities.ZonaIntervencionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface ZonaIntervencionToProgSesionesEntityMapper {

      // * Dep´s
      @Mappings({
                  @Mapping(target = "zonaIntervencion", ignore = true)
      })
      EquipoTrabajo toModel(EquipoTrabajoEntity source);

      @Mappings({
                  @Mapping(target = "zonaIntervencion", ignore = true)
      })
      Aliado toModel(AliadoEntity source);

      @Mappings({
                  @Mapping(target = "potencialesFamilias", ignore = true)
      })
      UnidadOrganica toModel(UnidadOrganicaEntity source);

      @Mappings({
                  @Mapping(target = "familia", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true),
                  @Mapping(target = "estadoFichas", ignore = true)
      })
      FamiliaIntegrante toModel(IntegranteFamiliaEntity source);

      @Mappings({
                  @Mapping(target = "zonaIntervencion", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true),
                  @Mapping(target = "motivosReferencia", ignore = true),
      })
      PotencialFamilia toModel(PotencialFamiliaEntity source);

      // * Model-To-Entity

      // * Entity-To-Model
      @Mappings({
                  @Mapping(target = "equiposTrabajo", ignore = true),
                  @Mapping(target = "aliados", ignore = true),
      })
      ZonaIntervencion toModel(ZonaIntervencionEntity source);

}
