package microservice.sigesu.anexorespuesta.repository;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.sql.DataSource;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.SqlLobValue;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import microservice.sigesu.anexorespuesta.dtos.AnexoCabeceraResponse;
import microservice.sigesu.anexorespuesta.dtos.AnexoEvaluacionResponse;
import microservice.sigesu.anexorespuesta.dtos.CreateAnexoEvaluacionRequest;
import microservice.sigesu.anexorespuesta.dtos.UpdateAnexoCompletoRequest;
import microservice.sigesu.anexorespuesta.mappers.AnexoRespuestaEntityMapper;
import microservice.sigesu.anexorespuesta.model.AnexoRespuesta;
import oracle.jdbc.OracleTypes;
import microservice.shared_data.dtos.projections.ReporteComparativoFasesFichaProjection;
import microservice.shared_data.dtos.querys.AnexoRespuestaQuery;
import microservice.shared_data.dtos.responses.EstadoAnexoProjectionResponse;
import microservice.shared_data.entities.AnexoRespuestaEntity;
import microservice.shared_data.enums.InabifServices;
import microservice.shared_data.repositories.BaseOracleRepository;

import org.springframework.transaction.annotation.Transactional;

@Repository
public class AnexoRespuestaRepositoryImpl extends BaseOracleRepository implements AnexoRespuestaRepository {

   private final AnexoRespuestaJpaRepository jpaRepository;
   private final AnexoRespuestaEntityMapper mapper;
   @PersistenceContext
   private EntityManager entityManager;

   public AnexoRespuestaRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource,
         AnexoRespuestaJpaRepository jpaRepository,
         AnexoRespuestaEntityMapper mapper) {
      super(jdbcTemplate, dataSource);
      this.jpaRepository = jpaRepository;
      this.mapper = mapper;
   }

   @Override
   public AnexoRespuesta save(AnexoRespuesta anexoRespuesta) {
      AnexoRespuestaEntity anexoRespuestaEntity = new AnexoRespuestaEntity();

      if (anexoRespuesta.getIdRespuesta() != null) { // * Actualiza
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
   public <M, Q> List<M> findAnexosRespuestasByQuerys(Integer idFamilia, Integer anexo, Integer grupo) {

      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_familia", idFamilia);
      inParams.put("p_anexo", anexo);
      inParams.put("p_grupo", grupo);

      return (List<M>) this.executeProcedureWithInParams("USP_BUSCAR_RESPUESTAS_POR_PARAMETROS", inParams,
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
      inParams.put("p_id_servicio", InabifServices.SIGESU.getId());
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
      inParams.put("p_id_servicio", InabifServices.SIGESU.getId());
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
      inParams.put("p_id_servicio", InabifServices.SIGESU.getId());
      inParams.put("p_num_anexo", numAnexo);
      inParams.put("p_fase", fase);
      inParams.put("p_id_familia", idFamilia);
      inParams.put("p_id_integrante", null);
      super.executeProcedureWithInParams("USP_ELIMINAR_ANEXO_RESPUESTAS_PARAMETRIZADO", inParams);

   }

   @Override
   public AnexoEvaluacionResponse crearAnexoCompleto(
         CreateAnexoEvaluacionRequest request) {

      try {

         // Convertir respuestas a JSON
         ObjectMapper mapper = new ObjectMapper();
         String respuestasJson = mapper.writeValueAsString(request.getRespuestas());

         // Crear stored procedure
         StoredProcedureQuery query = entityManager.createStoredProcedureQuery("USP_CREAR_ANEXO_COMPLETO");

         // =========================
         // Registrar parámetros IN
         // =========================
         query.registerStoredProcedureParameter("p_id_anexo", Long.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_id_centro", Long.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_fecha_aplicacion", java.sql.Date.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_fecha_registro", java.sql.Date.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_usu_registra", Integer.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_respuestas_json", String.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_id_resp_supervision", Integer.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_id_director", Long.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_id_supervisado", String.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_periodo", String.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_tipo", String.class, ParameterMode.IN);

         // =========================
         // Registrar parámetros OUT
         query.registerStoredProcedureParameter("p_id_cabecera", Long.class, ParameterMode.OUT);

         query.registerStoredProcedureParameter("p_correlativo", Integer.class, ParameterMode.OUT);
         // =========================

         // =========================
         // Setear parámetros IN
         // =========================
         query.setParameter("p_id_anexo", request.getIdAnexo());
         query.setParameter("p_id_centro", request.getIdCentro());
         query.setParameter(
               "p_fecha_aplicacion",
               request.getFechaAplicacion() != null
                     ? java.sql.Date.valueOf(request.getFechaAplicacion())
                     : null);
         query.setParameter(
               "p_fecha_registro",
               request.getFechaRegistro() != null
                     ? java.sql.Date.valueOf(request.getFechaRegistro())
                     : null);
         query.setParameter("p_usu_registra", request.getUsuRegistra());
         query.setParameter("p_respuestas_json", respuestasJson);
         query.setParameter("p_id_resp_supervision", request.getIdRespSupervision());
         query.setParameter("p_id_director", request.getIdDirector());
         query.setParameter("p_id_supervisado", request.getIdSupervisado());
         query.setParameter("p_periodo", request.getPeriodo());
         query.setParameter("p_tipo", request.getTipo());

         // =========================
         // Ejecutar procedure
         // =========================
         query.execute();

         // =========================
         // Obtener resultados OUT
         // =========================
         Number idCabeceraNum = (Number) query.getOutputParameterValue("p_id_cabecera");

         Number correlativoNum = (Number) query.getOutputParameterValue("p_correlativo");

         Long idCabecera = idCabeceraNum != null ? idCabeceraNum.longValue() : null;

         Integer correlativo = correlativoNum != null ? correlativoNum.intValue() : null;

         // =========================
         // Retornar respuesta
         // =========================
         return AnexoEvaluacionResponse.builder()
               .idCabecera(idCabecera)
               .correlativo(correlativo)
               .totalRespuestas(
                     request.getRespuestas() != null
                           ? request.getRespuestas().size()
                           : 0)
               .build();

      } catch (Exception e) {

         e.printStackTrace(); // importante para debug

         throw new RuntimeException(
               "Error al crear anexo completo: " + e.getMessage(),
               e);
      }
   }

   @Override
   public List<AnexoCabeceraResponse> listarAnexosCabecera() {

      StoredProcedureQuery query = entityManager.createStoredProcedureQuery("USP_LISTAR_ANEXOS_CABECERA");

      query.registerStoredProcedureParameter("p_cursor", void.class, ParameterMode.REF_CURSOR);
      query.execute();

      List<Object[]> rows = query.getResultList();
      List<AnexoCabeceraResponse> lista = new ArrayList<>();

      for (Object[] row : rows) {
         AnexoCabeceraResponse dto = AnexoCabeceraResponse.builder()
               .idAnexoCabecera(row[0] != null ? ((Number) row[0]).longValue() : null)
               .idAnexo(row[1] != null ? ((Number) row[1]).longValue() : null)
               .idCentro(row[2] != null ? ((Number) row[2]).longValue() : null)
               .correlativo(row[3] != null ? ((Number) row[3]).intValue() : null)
               .usuRegistra(row[4] != null ? ((Number) row[4]).intValue() : null)
               .estado(row[5] != null ? ((Number) row[5]).intValue() : null)
               .eliminado(row[6] != null ? ((Number) row[6]).intValue() : null)
               .codigoAnexo2((String) row[7])
               .nombreAnexo((String) row[8])
               .nombreUnidad((String) row[9])
               .nombreServicio((String) row[10])
               .nombreCentro((String) row[11])
               .periodo((String) row[12])
               .tipo((String) row[13])
               .reqValidacion(((Number) row[14]).intValue())
               .reqSupervisados(((Number) row[15]).intValue())
               .build();
         lista.add(dto);
      }

      return lista;
   }

   @Override
   public Map<String, Object> obtenerRespuestasPorAnexo(Long idAnexoCabecera, Integer correlativo) {
      return jdbcTemplate.execute(
            con -> {
               CallableStatement cs = con.prepareCall("{call SP_OBTENER_ANEXO_CABECERA(?, ?, ?)}");
               cs.setLong(1, idAnexoCabecera);
               cs.setInt(2, correlativo);
               cs.registerOutParameter(3, OracleTypes.CURSOR);
               return cs;
            },
            (CallableStatementCallback<Map<String, Object>>) cs -> {
               cs.execute();
               ResultSet rs = (ResultSet) cs.getObject(3);

               Map<String, Object> cabeceraMap = new HashMap<>();
               List<Map<String, Object>> listaRespuestas = new ArrayList<>();
               boolean cabeceraSet = false;

               while (rs.next()) {
                  // Solo llenamos la cabecera una vez
                  if (!cabeceraSet) {
                     cabeceraMap.put("idAnexoCabecera", rs.getLong("ID_ANEXO_CABECERA"));
                     cabeceraMap.put("codigoAnexo", rs.getString("CODIGO_ANEXO"));
                     cabeceraMap.put("nombreAnexo", rs.getString("NOMBRE_ANEXO"));
                     cabeceraMap.put("nombreServicio", rs.getString("NOMBRE_SERVICIO")); // <-- agregado
                     cabeceraMap.put("nombreCentro", rs.getString("NOMBRE_CENTRO"));
                     cabeceraMap.put("nombreUnidad", rs.getString("NOMBRE_UNIDAD"));
                     cabeceraMap.put("departamento", rs.getString("DEPARTAMENTO"));
                     cabeceraMap.put("provincia", rs.getString("PROVINCIA"));
                     cabeceraMap.put("distrito", rs.getString("DISTRITO")); // <-- asegurarse que el procedure lo
                     cabeceraMap.put("fechaRegistro", rs.getDate("FECHA_REGISTRO")); // devuelva correctamente
                     cabeceraMap.put("fechaAplicacion", rs.getDate("FECHA_APLICACION"));
                     cabeceraMap.put("correlativo", rs.getInt("CORRELATIVO"));
                     cabeceraMap.put("audioUrl", rs.getString("AUDIO_URL"));
                     cabeceraMap.put("idDirector", rs.getLong("IDDIRECTOR"));
                     cabeceraMap.put("respDirector", rs.getString("DIRECTOR"));
                     cabeceraMap.put("codigoAnexo2", rs.getString("CODIGO_ANEXO2"));
                     cabeceraMap.put("tipoCentro", rs.getString("TIPO_CENTRO"));
                     cabeceraMap.put("idRespSupervision", rs.getLong("ID_RESP_SUPERVISION"));
                     cabeceraMap.put("respSupervision", rs.getString("RESP_SUPERVISION"));
                     cabeceraMap.put("idSupervisado", rs.getString("ID_SUPERVISADO"));
                     cabeceraMap.put("idsPersonalValida", rs.getString("IDS_PERSONAL_VALIDA"));
                     cabeceraMap.put("estado", rs.getLong("ESTADO"));
                     // cabeceraMap.put("nombreSupervisado", rs.getString("SUPERVISADO"));
                     cabeceraSet = true;
                  }

                  // Llenamos las respuestas
                  Long idPregunta = rs.getLong("AP_ID_PREGUNTA");
                  if (idPregunta != null) {
                     Map<String, Object> respuestaMap = new HashMap<>();
                     respuestaMap.put("idPregunta", idPregunta);
                     respuestaMap.put("tipoControl", rs.getString("TIPO_CONTROL"));
                     respuestaMap.put("pregunta", rs.getString("PREGUNTA"));
                     // respuestaMap.put("pregunta", rs.getString("PREGUNTA") != null ?
                     // rs.getString("PREGUNTA").trim() : null);
                     respuestaMap.put("respuesta", rs.getString("RESPUESTA"));
                     respuestaMap.put("respuesta2", rs.getString("RESPUESTA2"));
                     respuestaMap.put("opciones", rs.getString("OPCIONES"));
                     respuestaMap.put("obligatoria", rs.getInt("OBLIGATORIA"));
                     respuestaMap.put("pregunta2", rs.getString("PREGUNTA2"));
                     respuestaMap.put("tipoControl2", rs.getString("TIPO_CONTROL2"));
                     respuestaMap.put("opciones2", rs.getString("OPCIONES2"));
                     respuestaMap.put("obligatoria2", rs.getInt("OBLIGATORIA2"));
                     respuestaMap.put("tipoDato1", rs.getString("TIPODATO1"));
                     respuestaMap.put("tipoDato2", rs.getString("TIPODATO2"));
                     respuestaMap.put("condicion", rs.getString("CONDICION"));
                     listaRespuestas.add(respuestaMap);
                  }
               }

               cabeceraMap.put("respuestas", listaRespuestas);
               System.out.println("Cabecera completa: " + cabeceraMap);
               return cabeceraMap;
            });
   }

   @Override
   @Transactional
   public AnexoEvaluacionResponse updateAnexoCompleto(UpdateAnexoCompletoRequest request) {
      try {
         ObjectMapper mapper = new ObjectMapper();
         String respuestasJson = mapper.writeValueAsString(request.getRespuestas());

         StoredProcedureQuery query = entityManager.createStoredProcedureQuery("USP_ACTUALIZAR_ANEXO_COMPLETO");

         query.registerStoredProcedureParameter("p_id_cabecera", Long.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_id_anexo", Long.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_id_centro", Long.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_fecha_aplicacion", java.sql.Date.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_fecha_registro", java.sql.Date.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_usu_modifica", Integer.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_id_resp_supervision", Long.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_id_director", Long.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_id_supervisado", String.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_respuestas_json", String.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_periodo", String.class, ParameterMode.IN);
         query.registerStoredProcedureParameter("p_tipo", String.class, ParameterMode.IN);

         // OUT parameter
         query.registerStoredProcedureParameter("p_correlativo", Integer.class, ParameterMode.OUT);

         query.setParameter("p_id_cabecera", request.getIdCabecera());
         query.setParameter("p_id_anexo", request.getIdAnexo());
         query.setParameter("p_id_centro", request.getIdCentro());
         query.setParameter("p_fecha_aplicacion", java.sql.Date.valueOf(request.getFechaAplicacion()));
         query.setParameter("p_fecha_registro", java.sql.Date.valueOf(request.getFechaRegistro()));
         query.setParameter("p_usu_modifica", request.getUsuModifica());
         query.setParameter("p_id_resp_supervision", request.getIdRespSupervision());
         query.setParameter("p_id_director", request.getIdDirector());
         query.setParameter("p_id_supervisado", request.getIdSupervisado());
         query.setParameter("p_respuestas_json", respuestasJson);
         query.setParameter("p_periodo", request.getPeriodo());
         query.setParameter("p_tipo", request.getTipo());

         query.execute();

         Number correlativoNum = (Number) query.getOutputParameterValue("p_correlativo");
         Integer correlativo = correlativoNum != null ? correlativoNum.intValue() : null;

         return AnexoEvaluacionResponse.builder()
               .idCabecera(request.getIdCabecera())
               .correlativo(correlativo)
               .totalRespuestas(request.getRespuestas().size())
               .build();

      } catch (Exception e) {
         throw new RuntimeException("Error al actualizar anexo completo", e);
      }
   }

   @Override
   @Transactional
   public void actualizarAudio(Long idAnexoCabecera, String audioUrl) {

      String sql = """
                UPDATE SSI_ANEXOS_CABECERA
                SET AUDIO_URL = ?
                WHERE ID_ANEXO_CABECERA = ?
            """;

      jdbcTemplate.update(sql, audioUrl, idAnexoCabecera);
   }

   @Override
   public List<Map<String, Object>> listarResponsablesSupervision(String abreviatura) {

      return jdbcTemplate.execute(
            con -> {
               CallableStatement cs = con.prepareCall("{call SP_LISTAR_RESP_SUPERVISION(?, ?)}");
               cs.setString(1, abreviatura);
               cs.registerOutParameter(2, OracleTypes.CURSOR);
               return cs;
            },
            (CallableStatementCallback<List<Map<String, Object>>>) cs -> {

               cs.execute();

               ResultSet rs = (ResultSet) cs.getObject(2);

               List<Map<String, Object>> lista = new ArrayList<>();

               while (rs.next()) {

                  Map<String, Object> map = new HashMap<>();

                  map.put("idPersonal", rs.getLong("IDPERSONAL"));
                  map.put("nombre", rs.getString("NOMBRES"));
                  map.put("unidad", rs.getString("UORNOMBRE"));

                  lista.add(map);
               }

               return lista;
            });
   }

   @Override
   public List<Map<String, Object>> listarResponsablesCentro(String nombreCentro, Long idUnidadOrganica) {

      return jdbcTemplate.execute(
            con -> {
               CallableStatement cs = con.prepareCall("{call SP_LISTAR_TRAB_CENTRO(?, ?, ?)}");
               cs.setString(1, nombreCentro);
               if (idUnidadOrganica != null) {
                  cs.setLong(2, idUnidadOrganica);
               } else {
                  cs.setNull(2, java.sql.Types.NUMERIC);
               }
               cs.registerOutParameter(3, OracleTypes.CURSOR);
               return cs;
            },
            (CallableStatementCallback<List<Map<String, Object>>>) cs -> {

               cs.execute();

               ResultSet rs = (ResultSet) cs.getObject(3);

               List<Map<String, Object>> lista = new ArrayList<>();

               while (rs.next()) {

                  Map<String, Object> map = new HashMap<>();

                  map.put("idPersonal", rs.getLong("IDPERSONAL"));
                  map.put("nombre", rs.getString("NOMBRES"));

                  lista.add(map);
               }

               return lista;
            });
   }

   @Override
   public void savePersonalValidaAnexoCabecera(Integer idCabecera, String idsPersonal) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_anexo_cabecera", idCabecera);
      inParams.put("p_ids_personal", idsPersonal);
      super.executeProcedureWithInParams("USP_SAVE_PERSONAL_VALIDA_ANEXO", inParams);
   }

   @Override
   public void saveConformidadAnexoCabecera(Integer idCabecera, Integer estado) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_anexo_cabecera", idCabecera);
      inParams.put("p_estado", estado);
      super.executeProcedureWithInParams("USP_SAVE_CONFORMIDAD_ANEXO_CABECERA", inParams);
   }

   @Override
   public void insertarAnexoCabeceraAudio(Long idAnexoCabecera, byte[] audio, String nombreArchivo) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_operacion", 1);
      inParams.put("p_id_audio", null);
      inParams.put("p_id_anexo_cabecera", idAnexoCabecera);
      inParams.put("p_audio", new SqlLobValue(audio));
      inParams.put("p_nombre_archivo", nombreArchivo);
      inParams.put("p_estado", null);
      super.executeProcedureWithInParams("USP_CRUD_ANEXO_CABECERA_AUDIO", inParams);
   }

   @Override
   public void actualizarAnexoCabeceraAudio(Long idAudio, byte[] audio, String nombreArchivo, Integer estado) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_operacion", 2);
      inParams.put("p_id_audio", idAudio);
      inParams.put("p_id_anexo_cabecera", null);
      inParams.put("p_audio", new SqlLobValue(audio));
      inParams.put("p_nombre_archivo", nombreArchivo);
      inParams.put("p_estado", estado);
      super.executeProcedureWithInParams("USP_CRUD_ANEXO_CABECERA_AUDIO", inParams);
   }

   @Override
   public void eliminarAnexoCabeceraAudio(Long idAudio) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_operacion", 3);
      inParams.put("p_id_audio", idAudio);
      inParams.put("p_id_anexo_cabecera", null);
      inParams.put("p_audio", null);
      inParams.put("p_nombre_archivo", null);
      inParams.put("p_estado", null);
      super.executeProcedureWithInParams("USP_CRUD_ANEXO_CABECERA_AUDIO", inParams);
   }

   @Override
   public List<Map<String, Object>> consultarAnexoCabeceraAudio(Long idAnexoCabecera) {
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_operacion", 4);
      inParams.put("p_id_audio", null);
      inParams.put("p_id_anexo_cabecera", idAnexoCabecera);
      inParams.put("p_audio", null);
      inParams.put("p_nombre_archivo", null);
      inParams.put("p_estado", null);
      return super.executeProcedureAndFetchResult("USP_CRUD_ANEXO_CABECERA_AUDIO", inParams, "p_cursor");
   }

   @Override
   public List<Map<String, Object>> listarAnexoCabeceraAudio(Long idAnexoCabecera) {
      List<Map<String, Object>> resultados = consultarAnexoCabeceraAudio(idAnexoCabecera);
      resultados.forEach(r -> r.remove("ACA_AUDIO"));
      return resultados;
   }
}
