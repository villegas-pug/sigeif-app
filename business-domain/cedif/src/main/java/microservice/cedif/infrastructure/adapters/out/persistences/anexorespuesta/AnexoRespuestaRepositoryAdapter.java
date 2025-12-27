package microservice.cedif.infrastructure.adapters.out.persistences.anexorespuesta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.cedif.domain.models.AnexoRespuesta;
import microservice.cedif.domain.ports.out.AnexoRespuestaRepositoryPort;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.querys.AnexoRespuestaQuery;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;
import microservice.shared_data.entities.AnexoRespuestaEntity;
import microservice.shared_data.enums.InabifServices;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class AnexoRespuestaRepositoryAdapter extends BaseOracleRepository implements AnexoRespuestaRepositoryPort {

   public AnexoRespuestaRepositoryAdapter(JdbcTemplate jdbcTemplate, DataSource dataSource) {
      super(jdbcTemplate, dataSource);
   }

   @Autowired
   private AnexoRespuestaJpaRepository jpaRepository;

   @Autowired
   private AnexoRespuestaEntityMapper mapper;

   @Override
   public AnexoRespuesta save(AnexoRespuesta anexoRespuesta) {
      AnexoRespuestaEntity anexoRespuestaEntity = new AnexoRespuestaEntity();

      if (anexoRespuesta.getIdRespuesta() != null) { // Actualiza
         anexoRespuestaEntity = this.jpaRepository.findById(anexoRespuesta.getIdRespuesta()).get();
      }

      this.mapper.toEntity(anexoRespuesta, anexoRespuestaEntity);
      return this.mapper.toModel(this.jpaRepository.save(anexoRespuestaEntity));

   }

   @Override
   public <M> List<M> saveAll(List<AnexoRespuesta> anexosRespuestasModel) {
      List<AnexoRespuesta> anexosResuestasEntities = anexosRespuestasModel.stream().map(this::save).toList();
      return (List<M>) anexosResuestasEntities;
   }

   @Override
   public List<AnexoRespuestaQuery> findAnexosRespuestasByQuerys(Integer idFamilia, Integer anexo, Integer grupo) {

      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_familia", idFamilia);
      inParams.put("p_anexo", anexo);
      inParams.put("p_grupo", grupo);

      return this.executeProcedureWithInParams("USP_BUSCAR_RESPUESTAS_POR_PARAMETROS", inParams,
            "c_resultado", AnexoRespuestaQuery.class);

   }

   @Override
   public <M, Q> List<M> findIntegranteAnexosRespuestasByQuerys(Integer idIntegrante, Integer anexo, Integer grupo) {

      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_integrante", idIntegrante);
      inParams.put("p_anexo", anexo);
      inParams.put("p_grupo", grupo);

      return (List<M>) this.executeProcedureWithInParams("USP_BUSCAR_INTEGRANTE_RESPUESTAS_POR_PARAMETROS",
            inParams,
            "c_resultado", AnexoRespuestaQuery.class);

   }

   @Override
   public Optional<AnexoRespuesta> findAnexoRespuestaById(Long idRespuesta) {
      return this.jpaRepository.findById(idRespuesta).map(mapper::toModel);
   }

   @Override
   public List<EstadoAnexoProjectionResponse> findEstadosAnexosByParams(Long idFamilia, Long idIntegrante) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_servicio", InabifServices.CEDIF.getId());
      inParams.put("p_id_familia", idFamilia);
      inParams.put("p_id_integrante", idIntegrante);

      return this.executeProcedureWithInParams("USP_LISTAR_ESTADOS_FICHAS_POR_PARAMS",
            inParams,
            "p_resultado_busqueda", EstadoAnexoProjectionResponse.class);
   }

   @Override
   public List<ReporteComparativoFasesFichaProjection> generateComparativeReportForFichaFasesByFilters(
         Integer numAnexo, Long idFamilia, Long idIntegrante) {

      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_servicio", InabifServices.CEDIF.getId());
      inParams.put("p_num_anexo", numAnexo);
      inParams.put("p_id_familia", idFamilia);
      inParams.put("p_id_integrante", idIntegrante);

      return this.executeProcedureWithInParams(
            "USP_GENERAR_REPORTE_COMPARATIVO_FASES_DE_FICHAS_PARAMETRIZADO",
            inParams,
            "p_resultado_busqueda",
            ReporteComparativoFasesFichaProjection.class);
   }

   @Override
   public void deleteAnexoRespuestasByParams(Integer numAnexo, Integer fase, Long idFamilia, Long idIntegrante) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_servicio", InabifServices.CEDIF.getId());
      inParams.put("p_num_anexo", numAnexo);
      inParams.put("p_fase", fase);
      inParams.put("p_id_familia", idFamilia);
      inParams.put("p_id_integrante", null);
      super.executeProcedureWithInParams("USP_ELIMINAR_ANEXO_RESPUESTAS_PARAMETRIZADO", inParams);

   }

}
