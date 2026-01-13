package microservice.cedif.infrastructure.adapters.out.persistences.potencialfamilia;

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
import microservice.cedif.domain.models.FamiliaIntegrante;
import microservice.cedif.domain.models.MotivoReferecia;
import microservice.cedif.domain.models.PotencialFamilia;
import microservice.cedif.domain.ports.in.potencialfamilia.PotencialFamiliaCreateCommand;
import microservice.cedif.domain.ports.out.PotencialFamiliaRepositoryPort;
import microservice.shared_data.entities.PotencialFamiliaEntity;
import microservice.shared_data.entities.UnidadOrganicaEntity;
import microservice.shared_data.enums.InabifServices;
import oracle.jdbc.driver.OracleConnection;

@Repository
@AllArgsConstructor
@Log4j2
public class PotencialFamiliaRepositoryAdapter implements PotencialFamiliaRepositoryPort {

   private final DataSource dataSource;
   private final JdbcTemplate jdbcTemplate;
   private final PotencialFamiliaJpaRepository jpaRepository;
   private final PotencialFamiliaEntityMapper mapper;

   @Override
   public void createPotecialFamilia(PotencialFamiliaCreateCommand potencialFamilia) {

      try (OracleConnection conn = dataSource.getConnection().unwrap(OracleConnection.class)) {

         // Familia motivo de referencia
         List<MotivoReferecia> familiaMotivosReferecia = Optional.ofNullable(potencialFamilia.getMotivosReferencia())
               .orElse(List.of()); // Permite valores nulos

         Struct[] motivoRefereciasStructs = familiaMotivosReferecia.stream().map(motivo -> {
            try {
               return conn.createStruct("O_FAMILIA_MOTIVO_REFERENCIA", new Object[] {
                     motivo.getIdMotivo()
               });
            } catch (SQLException e) {
               throw new RuntimeException("Error al crear estructura de familia motivo de referencia", e);
            }
         }).toArray(Struct[]::new);

         Array motivosRefereciasStructsArray = conn.createOracleArray("T_FAMILIA_MOTIVOS_REFERENCIA",
               motivoRefereciasStructs);

         // Integrantes de familia
         List<FamiliaIntegrante> integrantes = potencialFamilia.getIntegrantes();

         Struct[] integrantesStructs = integrantes.stream().map(integrante -> {

            try {

               return conn.createStruct("O_FAMILIA_INTEGRANTE", new Object[] {

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
                     Date.valueOf(integrante.getFecNac()),
                     integrante.getEdad(),
                     integrante.getTelefono(),
                     integrante.getCorreo(),
                     integrante.getIdDepartamento(),
                     integrante.getIdProvincia(),
                     integrante.getIdDistrito(),
                     integrante.getDireccion(),
                     integrante.getReferenciaDomiciliaria(),
                     integrante.getGradoSeccionNNA(),
                     integrante.getAnioAnteriorPromovido(),
                     integrante.getNombreInstitucionEducativa(),
                     integrante.getPeso(),
                     integrante.getTalla(),
                     integrante.getIngresosSoles(),
                     integrante.getGastosSoles(),
                     integrante.getIdDepartamentoNac(),
                     integrante.getIdProvinciaNac(),
                     integrante.getIdDistritoNac(),
                     integrante.getObservaciones(),
                     integrante.getDiagnosticoMedico(),
                     integrante.getEstablecimientoSalud(),
                     integrante.getPorCostumbresSeConsidera(),
                     integrante.getSituacionLaboral(),
                     integrante.getTieneCertMedico(),
                     integrante.getGradoDiscapacidad(),
                     integrante.getPerfilIngresoNna(),
                     integrante.getTipoEducacion(),
                     integrante.getVictimaIndirectaFeminicidio(),
                     integrante.getGestante(),
                     integrante.getLactante(),
                     integrante.getInscripcionConadis(),
                     integrante.getGradoInstruccion(),
                     integrante.getTipoDiscapacidad(),
                     integrante.getTieneDiscapacidad(),

                     // * Nuevo
                     Optional.ofNullable(integrante.getAlgunIntegranteTieneProblemaSalud()).orElse(0),
                     integrante.getViaIngresoNnaCedif(),
                     integrante.getMedioIngresoNnaCedif(),
                     integrante.getDescripcionOcupacion(),

                     integrante.getCuidador(),
                     integrante.getUsuRegistra()

               });

            } catch (Exception e) {
               throw new RuntimeException("Error al crear estructura de integrantes de la familia", e);
            }

         }).toArray(Struct[]::new);

         Array integrantesStructsArray = conn.createOracleArray("T_FAMILIA_INTEGRANTES", integrantesStructs);

         // Potencia familia
         Struct potencialFamiliaStruct = conn.createStruct("O_POTENCIAL_FAMILIA", new Object[] {
               null, // ! Generado automaticamente
               null,
               null,
               potencialFamilia.getIdUnidadOrganica(),
               InabifServices.CEDIF.getId(),
               null,
               potencialFamilia.getUsuRegistra(),
               Date.valueOf((potencialFamilia.getFecRegistra())),
               motivosRefereciasStructsArray,
               integrantesStructsArray
         });

         // Respuesta de anexos

         Struct[] anexosRespuestasStructs = potencialFamilia.getAnexosRespuestas().stream().map(respuesta -> {
            try {
               return conn.createStruct("O_ANEXO_RESPUESTA", new Object[] {
                     respuesta.getIdPregunta(),
                     respuesta.getRespuesta(),
                     respuesta.getObservacion(),
                     respuesta.getIdPersonal(),
                     respuesta.getUsuRegistra()
               });
            } catch (Exception e) {
               throw new RuntimeException("Error al crear estructura de respuestas", e);
            }
         }).toArray(Struct[]::new);

         Array anexosRespuestasStructsArray = conn.createOracleArray("T_ANEXO_RESPUESTAS", anexosRespuestasStructs);

         // Final
         SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
         jdbcCall.withProcedureName("USP_GUARDAR_POTENCIAL_FAMILIA");

         Map<String, Object> inParams = Map.of(
               "p_servicio", InabifServices.CEDIF.getId(), // Cedif
               "p_potencial_familia", potencialFamiliaStruct,
               "p_fichas_respuestas", anexosRespuestasStructsArray);

         jdbcCall.execute(inParams);

      } catch (SQLException e) {
         log.error("Error al guardar: {}", e.getMessage(), e);
         throw new RuntimeException("Error al guardar la familia potencial ", e);
      }
   }

   @Override
   public Optional<PotencialFamilia> findPotencialFamiliaById(Long idFamilia) {
      return this.jpaRepository.findById(idFamilia).map(this.mapper::toModel);
   }

   @Override
   public <M> M updatePotencialFamilia(PotencialFamilia potencialFamilia) {

      PotencialFamiliaEntity oldEntity = this.jpaRepository.findById(potencialFamilia.getIdFamilia()).get();

      this.mapper.fromModelToEntity(potencialFamilia, oldEntity); // Actualiza del modelo a la copia

      oldEntity.setUnidadOrganica(
            UnidadOrganicaEntity.builder().idUO(potencialFamilia.getUnidadOrganica().getIdUO()).build());

      PotencialFamiliaEntity updatedEntity = this.jpaRepository.save(oldEntity);

      return (M) updatedEntity;

   }

   @Override
   public <M> M deletePotencialFamiliaById(Long idFamilia) {
      PotencialFamiliaEntity entity = this.jpaRepository.findById(idFamilia).get();
      entity.setEliminado(1); // Cambio de estado
      return (M) entity;
   }

   // ! Eliminar solo para prueba
   @Override
   public List<PotencialFamilia> findAllPotencialesFamilias() {
      List<PotencialFamilia> models = this.mapper.toModels(this.jpaRepository.findAll());
      return models;
   }

   @Override
   public List<PotencialFamilia> findPotencialesFamiliasByIds(Set<Long> idsFamilia) {
      List<PotencialFamiliaEntity> entities = this.jpaRepository.findAllById(idsFamilia);
      return entities.stream().map(this.mapper::toModel).toList();
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
