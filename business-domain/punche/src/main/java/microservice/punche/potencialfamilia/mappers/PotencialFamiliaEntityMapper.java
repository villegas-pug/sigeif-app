package microservice.punche.potencialfamilia.mappers;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import microservice.punche.aliado.model.Aliado;
import microservice.punche.anexorespuesta.model.AnexoRespuesta;
import microservice.punche.familiaintegrante.model.FamiliaIntegrante;
import microservice.punche.motivoreferecia.model.MotivoReferecia;
import microservice.punche.personal.model.Personal;
import microservice.punche.potencialfamilia.dtos.PotencialFamiliaResponse;
import microservice.punche.potencialfamilia.model.PotencialFamilia;
import microservice.punche.unidadorganica.model.UnidadOrganica;
import microservice.punche.zona.model.ZonaIntervencion;
import microservice.shared_data.entities.AliadoEntity;
import microservice.shared_data.entities.AnexoRespuestaEntity;
import microservice.shared_data.entities.FamiliaMotivoReferenciaEntity;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.entities.PersonalEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.entities.UnidadOrganicaEntity;
import microservice.shared_data.entities.ZonaIntervencionEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface PotencialFamiliaEntityMapper {

      // * Dep´s

      @Mapping(target = "potencialesFamilias", ignore = true)
      UnidadOrganica toUnidadOrganicaModel(UnidadOrganicaEntity entity);

      @Mapping(target = "familia", ignore = true)
      AnexoRespuesta toAnexoRespuestaModel(AnexoRespuestaEntity entity);

      @Mappings({
                  @Mapping(target = "familia", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true)
      })
      FamiliaIntegrante toFamiliaIntegranteModel(IntegranteFamiliaEntity entity);

      @Mappings({
                  @Mapping(target = "zonaIntervencion", ignore = true),
                  // @Mapping(target = "institucion", ignore = true),
                  @Mapping(target = "contactos", ignore = true),
      })
      Aliado toModel(AliadoEntity entity);

      // * Entity-To-Response
      @Mappings({
                  @Mapping(target = "zonaIntervencion", source = "zonaIntervencion", qualifiedByName = "mapZonaIntervencionEntityToZonaIntervencion"),
                  @Mapping(source = "motivosReferencia", target = "motivosReferencia", qualifiedByName = "mapFamiliaMotivoReferenciaEntityToMotivoReferecia")
      })
      PotencialFamiliaResponse toResponse(PotencialFamiliaEntity entity);

      // * Model-To-Entity
      @Mappings({
                  @Mapping(target = "unidadOrganica", ignore = true),
                  @Mapping(target = "integrantesFamilia", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true), // ! Deprecated
                  @Mapping(source = "acompañante", target = "acompañante", qualifiedByName = "mapPersonalToEntity")
      })
      PotencialFamiliaEntity toEntity(PotencialFamilia model);

      @Mappings({
                  @Mapping(source = "acompañante", target = "acompañante", qualifiedByName = "mapPersonalToEntity")
      })
      void fromModelToEntity(PotencialFamilia source, @MappingTarget PotencialFamiliaEntity target);

      // * Entity-To-Model

      @Mappings({
                  @Mapping(target = "zonaIntervencion", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true),
      })
      PotencialFamilia toModel(PotencialFamiliaEntity entity);

      List<PotencialFamilia> toModels(List<PotencialFamiliaEntity> entities);

      Set<PotencialFamilia> toModels(Set<PotencialFamiliaEntity> entities);

      // * Default method's
      @Named("mapFamiliaMotivoReferenciaEntityToMotivoReferecia")
      default List<MotivoReferecia> mapFamiliaMotivoReferenciaEntityToMotivoReferecia(
                  Set<FamiliaMotivoReferenciaEntity> entities) {
            return entities != null ? entities.stream().map(entity -> {
                  return MotivoReferecia
                              .builder()
                              .descripcion(entity.getMotivo() != null ? entity.getMotivo().getDescripcion() : null)
                              .idMotivo(entity.getMotivo() != null ? entity.getMotivo().getIdMotivo() : null)
                              .build();
            }).toList() : List.of();

      }

      @Named("mapZonaIntervencionEntityToZonaIntervencion")
      default ZonaIntervencion mapZonaIntervencionEntityToZonaIntervencion(ZonaIntervencionEntity entity) {
            return entity != null ? ZonaIntervencion
                        .builder()
                        .idZona(entity.getIdZona())
                        .unidadOrg(this.toUnidadOrganicaModel(entity.getUnidadOrganica()))
                        .build()
                        : null;
      }

      @Named("mapPersonalToEntity")
      default PersonalEntity mapIdPersonalToEntity(Personal personal) {
            return personal != null ? PersonalEntity
                        .builder()
                        .idPersonal(personal.getIdPersonal())
                        .build() : null;

      }

}
