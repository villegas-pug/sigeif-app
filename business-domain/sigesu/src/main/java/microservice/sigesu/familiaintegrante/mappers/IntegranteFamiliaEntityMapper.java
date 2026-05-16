package microservice.sigesu.familiaintegrante.mappers;

import java.util.List;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import microservice.sigesu.familiaintegrante.model.FamiliaIntegrante;
import microservice.shared_data.entities.CatalogoEntity;
import microservice.shared_data.entities.DocumentoEntity;
import microservice.shared_data.entities.IntegranteFamiliaEntity;
import microservice.shared_data.entities.PaisEntity;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.mappers.BaseMapStructConfig;

@Mapper(config = BaseMapStructConfig.class)
public interface IntegranteFamiliaEntityMapper {

      // ! Handler recursion exception

      // Método principal que actualiza la entidad existente
      @Mappings({
                  @Mapping(target = "familia", source = "idFamilia", qualifiedByName = "idToFamiliaEntity"),
                  @Mapping(target = "tipdoc", source = "idTipdoc", qualifiedByName = "idToDocumentoEntity"),
                  @Mapping(target = "gradoInst", source = "idGradoInst", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "tipoSeguro", source = "idTipoSeguro", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "nacionalidad", source = "idNac", qualifiedByName = "idToPaisEntity"),
                  @Mapping(target = "paisNacimiento", source = "idPaisNacimiento", qualifiedByName = "idToPaisEntity"),
                  @Mapping(target = "parentesco", source = "idParentesco", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "estadoCivil", source = "idEstadoCivil", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "sexo", source = "idSexo", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "ocupacion", source = "idOcupacion", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "idioma", source = "idIdioma", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "discapacidad", source = "idDiscapacidad", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "derivadoPor", source = "idDerivadoPor", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "servicioCuidador", source = "idServicioCuidador", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "centroPobla", source = "idCentroPobla", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "lenguaMaterna", source = "idLenguaMaterna", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "tipoVivienda", source = "idTipoVivienda", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "ubicacionVivienda", source = "idUbicacionVivienda", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "etnia", source = "idEtnia", qualifiedByName = "idToCatalogoEntity"),
                  @Mapping(target = "tipoFamilia", source = "idTipoFamilia", qualifiedByName = "idToCatalogoEntity")
      })
      void fromModelToEntity(FamiliaIntegrante source, @MappingTarget IntegranteFamiliaEntity target);

      // Método para convertir entidad a modelo
      @Mappings({
                  @Mapping(target = "familia", ignore = true),
                  @Mapping(target = "anexosRespuestas", ignore = true)
      })
      FamiliaIntegrante toModel(IntegranteFamiliaEntity entity);

      List<FamiliaIntegrante> toModels(List<IntegranteFamiliaEntity> entities);

      Set<FamiliaIntegrante> toModels(Set<IntegranteFamiliaEntity> entities);

      // Métodos de mapeo para crear entidades a partir de IDs

      @Named("idToFamiliaEntity")
      default PotencialFamiliaEntity mapIdToPotencialFamiliaEntity(Long id) {
            return id != null ? PotencialFamiliaEntity.builder().idFamilia(id).build() : null;
      }

      @Named("idToDocumentoEntity")
      default DocumentoEntity mapIdToDocumentoEntity(Long id) {
            return id != null ? DocumentoEntity.builder().idTipoDoc(id).build() : null;
      }

      @Named("idToCatalogoEntity")
      default CatalogoEntity mapIdToCatalogoEntity(Long id) {
            return id != null ? CatalogoEntity.builder().idCatalogo(id).build() : null;
      }

      @Named("idToPaisEntity")
      default PaisEntity mapIdToPaisEntity(Long id) {
            return id != null ? PaisEntity.builder().idPais(id).build() : null;
      }

}
