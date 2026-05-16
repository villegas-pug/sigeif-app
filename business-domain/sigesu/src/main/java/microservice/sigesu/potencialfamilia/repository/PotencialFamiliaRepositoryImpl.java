package microservice.sigesu.potencialfamilia.repository;

import java.sql.Array;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.sigesu.anexorespuesta.model.AnexoRespuesta;
import microservice.sigesu.familiaintegrante.model.FamiliaIntegrante;
import microservice.sigesu.motivoreferecia.model.MotivoReferecia;
import microservice.sigesu.potencialfamilia.dtos.PotencialFamiliaResponse;
import microservice.sigesu.potencialfamilia.mappers.PotencialFamiliaEntityMapper;
import microservice.sigesu.potencialfamilia.model.PotencialFamilia;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.enums.InabifServices;
import oracle.jdbc.driver.OracleConnection;

@Repository
@AllArgsConstructor
@Log4j2
public class PotencialFamiliaRepositoryImpl implements PotencialFamiliaRepository {

   private final DataSource dataSource;
   private final JdbcTemplate jdbcTemplate;
   private final PotencialFamiliaJpaRepository jpaRepository;
   private final PotencialFamiliaEntityMapper mapper;

   @Override
   public void savePotecialFamilia(PotencialFamilia potencialFamilia) {

      try (OracleConnection conn = dataSource.getConnection().unwrap(OracleConnection.class)) {

         // Familia motivo de referencia
         List<MotivoReferecia> familiaMotivosReferecia = Optional.ofNullable(potencialFamilia.getMotivosReferencia())
               .orElse(List.of()); // Permite valores nulos

         Struct[] motivoRefereciasStructs = familiaMotivosReferecia.stream().map(motivo -> {
            try {
               return conn.createStruct("O_FAMILIA_MOTIVO_REFERENCIA_V2", new Object[] {
                     motivo.getIdMotivo()
               });
            } catch (SQLException e) {
               throw new RuntimeException("Error al crear estructura de familia motivo de referencia", e);
            }
         }).toArray(Struct[]::new);

         Array motivosRefereciasStructsArray = conn.createOracleArray("T_FAMILIA_MOTIVOS_REFERENCIA_V2",
               motivoRefereciasStructs);

         // Integrantes de familia
         Set<FamiliaIntegrante> integrantes = Optional.ofNullable(potencialFamilia.getIntegrantesFamilia())
               .orElse(Set.of()); // Permite valores nulos

         Struct[] integrantesStructs = integrantes.stream().map(integrante -> {

            try {

               return conn.createStruct("O_FAMILIA_INTEGRANTE_V2", new Object[] {

                     integrante.getIdIntegrante(), // Recibe `id` para actualización
                     integrante.getIdTipdoc(),
                     integrante.getIdGradoInst(),
                     integrante.getIdTipoSeguro(),
                     integrante.getIdNac(),
                     integrante.getIdPaisNacimiento(),
                     integrante.getIdParentesco(),
                     integrante.getIdEstadoCivil(),
                     integrante.getIdSexo(),

                     integrante.getIdIdioma(),
                     integrante.getIdDiscapacidad(),
                     integrante.getIdDerivadoPor(),
                     integrante.getIdServicioCuidador(),

                     integrante.getIdCentroPobla(),
                     integrante.getIdOcupacion(),
                     integrante.getNumeroDoc(),
                     integrante.getNombres(),
                     integrante.getPrimerApe(),
                     integrante.getSegundoApe(),
                     integrante.getApellidoCasado(),
                     Optional.ofNullable(integrante.getFecNac()).map(Date::valueOf).orElse(null),
                     integrante.getTelefono(),
                     integrante.getCorreo(),
                     integrante.getIdDepartamento(),
                     integrante.getIdProvincia(),
                     integrante.getIdDistrito(),
                     integrante.getDireccion(),
                     integrante.getReferenciaDomiciliaria(),
                     integrante.getGradoSeccionNNA(),
                     integrante.getCentroPoblado(),
                     integrante.getCuidador(),
                     integrante.getUsuRegistra()

               });

            } catch (Exception e) {
               throw new RuntimeException("Error al crear estructura de integrantes de la familia", e);
            }

         }).toArray(Struct[]::new);

         Array integrantesStructsArray = conn.createOracleArray("T_FAMILIA_INTEGRANTES_V2", integrantesStructs);

         // Potencia familia
         Struct potencialFamiliaStruct = conn.createStruct("O_POTENCIAL_FAMILIA_V2", new Object[] {
               potencialFamilia.getIdFamilia(),
               null, // ! potencialFamilia.getCodFamilia()
               potencialFamilia.getZonaIntervencion().getIdZona(),
               potencialFamilia.getAliado().getIdAliado(),
               null, // * Para Cedif
               InabifServices.PUNCHE.getId(),
               potencialFamilia.getObservaciones(),
               potencialFamilia.getUsuRegistra(),
               Date.valueOf((potencialFamilia.getFecRegistra())),
               motivosRefereciasStructsArray,
               integrantesStructsArray
         });

         // Respuesta de anexos
         Set<AnexoRespuesta> anexosRespuestas = Optional.ofNullable(potencialFamilia.getAnexosRespuestas())
               .orElse(Set.of()); // Permite valores nulos

         Struct[] anexosRespuestasStructs = anexosRespuestas.stream().map(respuesta -> {
            try {
               return conn.createStruct("O_ANEXO_RESPUESTA_V2", new Object[] {
                     respuesta.getIdRespuesta(), // Recibe `id` para actualización
                     respuesta.getPregunta().getIdPregunta(),
                     respuesta.getRespuesta(),
                     respuesta.getObservacion(),
                     respuesta.getUsuRegistra()
               });
            } catch (Exception e) {
               throw new RuntimeException("Error al crear estructura de respuestas", e);
            }
         }).toArray(Struct[]::new);

         Array anexosRespuestasStructsArray = conn.createOracleArray("T_ANEXO_RESPUESTAS_V2", anexosRespuestasStructs);

         // Final
         SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
         jdbcCall.withProcedureName("USP_GUARDAR_POTENCIAL_FAMILIA_V2");

         Map<String, Object> inParams = Map.of(
               "p_potencial_familia", potencialFamiliaStruct,
               "p_fichas_respuestas", anexosRespuestasStructsArray);

         jdbcCall.execute(inParams);

      } catch (SQLException e) {
         log.error("Error al guardar: {}", e.getMessage(), e);
         throw new RuntimeException("Error al guardar la familia potencial ", e);
      }
   }

   @Override
   public Optional<PotencialFamiliaResponse> findPotencialFamiliaById(Long idFamilia) {
      Optional<PotencialFamiliaEntity> entity = this.jpaRepository.findById(idFamilia);
      return Optional.of(this.mapper.toResponse(entity.orElse(new PotencialFamiliaEntity())));
   }

   @Override
   public void deletePotencialFamiliaById(Long idFamilia) {
      PotencialFamiliaEntity entity = this.jpaRepository.findById(idFamilia).get();
      entity.setEliminado(1); // Cambio de estado
   }

   @Override
   public List<PotencialFamiliaResponse> findPotencialesFamiliasByIds(Set<Long> idsFamilia) {
      List<PotencialFamiliaEntity> entities = this.jpaRepository.findAllById(idsFamilia);
      return entities.stream().map(this.mapper::toResponse).toList();
   }

   @Override
   public PotencialFamilia partialUpdatePotecialFamilia(PotencialFamilia potencialFamilia) {
      return this.jpaRepository
            .findById(potencialFamilia.getIdFamilia())
            .map(potencialFamiliaPersist -> {
               this.mapper.fromModelToEntity(potencialFamilia, potencialFamiliaPersist);
               return potencialFamiliaPersist;
            })
            .map(this.mapper::toModel)
            .orElse(potencialFamilia);

   }

}
