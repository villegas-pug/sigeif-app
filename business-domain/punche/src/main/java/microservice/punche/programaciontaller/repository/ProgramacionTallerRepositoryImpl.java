package microservice.punche.programaciontaller.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.punche.programaciontaller.mappers.ProgramacionTallerEntityMapper;
import microservice.punche.programaciontaller.model.ProgramacionTaller;
import microservice.punche.programaciontallerfamilia.repository.ProgramacionTallerFamiliaJpaRepositiry;
import microservice.punche.taller.mappers.TallerEntityMapper;
import microservice.punche.taller.repository.TallerJpaRepository;
import microservice.shared_data.dtos.responses.ProgramacionTallerProjectionResponse;
import microservice.shared_data.entities.ProgramacionTallerEntity;
import microservice.shared_data.entities.ProgramacionTallerFamiliaEntity;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class ProgramacionTallerRepositoryImpl extends BaseOracleRepository implements ProgramacionTallerRepository {

      private final ProgramacionTallerJpaRepository progTallerJpaRepo;
      private final TallerJpaRepository tallerJpaRepo;
      private final ProgramacionTallerFamiliaJpaRepositiry progTallerFamJpaRepo;
      private final ProgramacionTallerEntityMapper mapper;
      private final TallerEntityMapper tallerMapper;

      public ProgramacionTallerRepositoryImpl(
                  JdbcTemplate jdbcTemplate,
                  DataSource dataSource,
                  ProgramacionTallerJpaRepository progTallerJpaRepo,
                  TallerJpaRepository tallerJpaRepo,
                  ProgramacionTallerFamiliaJpaRepositiry progTallerFamJpaRepo,
                  ProgramacionTallerEntityMapper mapper,
                  TallerEntityMapper tallerMapper) {
            super(jdbcTemplate, dataSource);
            this.progTallerJpaRepo = progTallerJpaRepo;
            this.tallerJpaRepo = tallerJpaRepo;
            this.progTallerFamJpaRepo = progTallerFamJpaRepo;
            this.mapper = mapper;
            this.tallerMapper = tallerMapper;
      }

      @Override
      public ProgramacionTaller createProgramacionTaller(ProgramacionTaller progTaller) {

            // * 1. Cabecera:

            // * 1.1 Nuevo taller
            TallerEntity newTaller = progTaller.getTaller().getIdTaller() == null
                        ? this.tallerJpaRepo.save(this.tallerMapper.toEntity(progTaller.getTaller()))
                        : null;

            // * 1.2 ...
            ProgramacionTallerEntity newProgTaller = new ProgramacionTallerEntity();
            this.mapper.fromModelToEntity(progTaller, newProgTaller);
            if (newTaller != null)
                  newProgTaller.setTaller(newTaller); // * Nuevo taller
            var createdProgTaller = this.progTallerJpaRepo.save(newProgTaller);

            // * 2. Detalle:
            newProgTaller.getTallerFamilias()
                        .stream()
                        .peek(tallerFamilia -> tallerFamilia.setProgTaller(createdProgTaller))
                        .forEach(this.progTallerFamJpaRepo::save);

            return this.mapper.toModel(createdProgTaller);

      }

      @Override
      public ProgramacionTaller updateProgramacionTaller(ProgramacionTaller programacionTaller) {

            // * 1. Cabecera:

            // * 1.1 Nuevo taller
            TallerEntity newTaller = programacionTaller.getTaller().getIdTaller() == null
                        ? this.tallerJpaRepo.save(this.tallerMapper.toEntity(programacionTaller.getTaller()))
                        : null;

            // * Entidad ProgramacionTallerEntity que fué persistido
            ProgramacionTallerEntity oldProgTallerEntity = this.progTallerJpaRepo
                        .findById(programacionTaller.getIdProgTaller())
                        .get();

            // * Entidades ProgramacionTallerFamiliaEntity que fueron persistidas
            List<ProgramacionTallerFamiliaEntity> oldProgTallerFamiliaEntity = new ArrayList<>(
                        oldProgTallerEntity.getTallerFamilias());

            this.mapper.fromModelToEntity(programacionTaller, oldProgTallerEntity); // * Mapea modelo a entidad
            if (newTaller != null) // * Suministra nuevo taller
                  oldProgTallerEntity.setTaller(newTaller);
            var updatedProgTallerPersit = this.progTallerJpaRepo.save(oldProgTallerEntity); // * Persiste

            // * 2. Detalle:

            // * Id's familias persistidas
            Set<Long> idsFamiliasPersist = oldProgTallerFamiliaEntity.stream()
                        .map(progTallerFam -> progTallerFam.getFamilia().getIdFamilia())
                        .collect(Collectors.toSet());

            // * Id's familias para actualizar
            Set<Long> idsFamiliasToUpdate = programacionTaller.getTallerFamilias().stream()
                        .map(progTallerFam -> progTallerFam.getFamilia().getIdFamilia())
                        .collect(Collectors.toSet());

            /*
             * ░ Casos de uso:
             * 
             * 1. Cuando familia existe en db, debe actualiza props.
             * 2. Cuando familias para actualización es menor a la persistida.
             * 3. Cuando familias son nuevas.
             * 
             */

            // * 1. Caso de uso: Cuando familia existe en db, debe actualiza props.
            // List<ProgramacionTallerFamiliaEntity> testTallerFamilias =
            oldProgTallerFamiliaEntity
                        .stream()
                        .filter(oldTallFamiliaEntity -> idsFamiliasToUpdate
                                    .contains(oldTallFamiliaEntity.getFamilia().getIdFamilia()))
                        .flatMap(oldTallFamiliaEntity -> {

                              // ! Puede existir 1 a más registros de una familia.
                              return programacionTaller.getTallerFamilias()
                                          .stream()
                                          .filter(updateProgTallerFamilia -> updateProgTallerFamilia.getFamilia()
                                                      .getIdFamilia().equals(oldTallFamiliaEntity
                                                                  .getFamilia()
                                                                  .getIdFamilia()))
                                          .map(updateProgTallerFamilia -> {
                                                this.mapper.fromModelToEntity(updateProgTallerFamilia,
                                                            oldTallFamiliaEntity);
                                                // ! Cambia estado en caso se eliminó
                                                oldTallFamiliaEntity.setEliminado(0);
                                                return oldTallFamiliaEntity;
                                          });

                        })
                        .forEach(this.progTallerFamJpaRepo::save); // Persiste

            // * 2. Caso de uso: Cuando familias para actualización es menor a la
            // * persistida.
            oldProgTallerFamiliaEntity
                        .stream()
                        // ? Familia no existe en la actualización, debe cambiar el estado a eliminado
                        .filter(tallFamiliaEntity -> !idsFamiliasToUpdate
                                    .contains(tallFamiliaEntity.getFamilia().getIdFamilia()))
                        .forEach(tallFamiliaEntity -> {
                              tallFamiliaEntity.setEliminado(1); // Eliminado
                              this.progTallerFamJpaRepo.save(tallFamiliaEntity);
                        });

            // * 3. Caso de uso: Cuando familias son nuevas.
            programacionTaller.getTallerFamilias()
                        .stream()
                        .filter(newTallFamilia -> !idsFamiliasPersist
                                    .contains(newTallFamilia.getFamilia().getIdFamilia()))
                        .map(this.mapper::toEntity)
                        .peek(newTallFamilia -> newTallFamilia.setProgTaller(updatedProgTallerPersit))
                        .forEach(this.progTallerFamJpaRepo::save);

            return this.mapper.toModel(oldProgTallerEntity);
      }

      @Override
      public Optional<ProgramacionTaller> findProgramacionTallerById(Long idProgTaller) {
            var progTaller = this.progTallerJpaRepo.findById(idProgTaller)
                        .map(this.mapper::toModel);

            return progTaller;
      }

      @Override
      public void deleteProgramacionTallerById(Long idProgTaller) {
            this.progTallerJpaRepo.findById(idProgTaller)
                        .ifPresent(progTaller -> {
                              progTaller.setEliminado(1);
                              this.progTallerJpaRepo.save(progTaller);
                        });
      }

      @Override
      public List<ProgramacionTallerProjectionResponse> findProgramacionTalleresByParams(Integer idServicio,
                  Integer anio,
                  Integer mes) {

            Map<String, Object> params = new HashMap<>();
            params.put("p_id_servicio", idServicio);
            params.put("p_anio", anio);
            params.put("p_mes", mes);

            return this.executeProcedureWithInParams("USP_BUSCAR_PROGRAMACION_TALLERES_POR_PARAMETROS", params,
                        "p_resultado_busqueda", ProgramacionTallerProjectionResponse.class);
      }

      @Override
      public void uploadAnexoProgramacionTaller(Long idProgTaller, String anexoName, byte[] anexo) {
            this.progTallerJpaRepo.findById(idProgTaller)
                        .ifPresent(progTaller -> {
                              progTaller.setAnexo(anexo);
                              progTaller.setAnexoNombre(anexoName);
                              this.progTallerJpaRepo.save(progTaller);
                        });
      }

}
