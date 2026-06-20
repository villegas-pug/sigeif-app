package microservice.educalle.detpatfam.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.educalle.detpatfam.mappers.DetPatfamEntityMapper;
import microservice.educalle.patfam.models.DetPatfam;
import microservice.educalle.taller.model.Taller;
import microservice.shared_data.dtos.responses.DetPatfamProjectionResponse;
import microservice.shared_data.entities.DetPatfamEntity;
import microservice.shared_data.entities.ModuloEntity;
import microservice.shared_data.entities.ObjetivoEspecificoEntity;
import microservice.shared_data.entities.TallerEntity;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class DetPatfamRepositoryImpl extends BaseOracleRepository implements DetPatfamRepository {

   private final DetPatfamJpaRepository jpaRepository;
   private final DetPatfamEntityMapper mapper;

   DetPatfamRepositoryImpl(
         JdbcTemplate jdbcTemplate,
         DataSource dataSource,
         DetPatfamJpaRepository jpaRepository,
         DetPatfamEntityMapper mapper) {
      super(jdbcTemplate, dataSource);
      this.jpaRepository = jpaRepository;
      this.mapper = mapper;
   }

   @Override
   public void deleteDetPatfamById(Long id) {
      jpaRepository.findById(id).map(detpatfam -> {
         detpatfam.setEliminado(1);
         return detpatfam;
      }).ifPresent(jpaRepository::save);

   }

   @Override
   public Optional<DetPatfam> findDetPatfamById(Long idDetPatfam) {
      return this.jpaRepository.findById(idDetPatfam).map(this.mapper::toModel);
   }

   @Override
   public List<DetPatfam> findDetPatfamByTaller(Taller taller) {
      return this.jpaRepository.findByTaller(TallerEntity.builder().idTaller(taller.getIdTaller()).build())
            .stream()
            .map(this.mapper::toModel)
            .toList();
   }

   @Override
   public List<DetPatfam> findDetPatfamByParams(Integer idServicio, Integer idDynamic) {

      List<DetPatfamEntity> detPatfams = switch (idServicio) {
         case 1 -> this.jpaRepository.findByModulo(ModuloEntity.builder().idModulo(idDynamic).build()); // * Cedif
         case 2 -> this.jpaRepository.findByTaller(TallerEntity.builder().idTaller(idDynamic).build()); // * educalle
         // * Acercandonos
         case 3 -> this.jpaRepository.findByObjetivo(ObjetivoEspecificoEntity.builder().idObjetivo(idDynamic).build());
         default -> List.of();
      };

      return detPatfams
            .stream()
            .collect(Collectors.toMap(
                  detPatfam -> detPatfam.getPatfam().getFamilia().getIdFamilia(),
                  detPatfam -> detPatfam,
                  (detPatfam1, detPatfam2) -> detPatfam1))
            .values()
            .stream()
            .map(this.mapper::toModel)
            .toList();
   }

   @Override
   public List<DetPatfamProjectionResponse> findDetPatfamByIdTaller(Integer idTaller) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_taller", idTaller);
      return super.executeProcedureWithInParams("USP_BUSCAR_DET_PATFAM_POR_TALLER", inParams,
            "p_resultado_busqueda", DetPatfamProjectionResponse.class);
   }

}
