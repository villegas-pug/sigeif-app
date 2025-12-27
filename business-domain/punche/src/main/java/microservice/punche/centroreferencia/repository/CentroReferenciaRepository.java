package microservice.punche.centroreferencia.repository;

import java.sql.SQLException;
import java.sql.Struct;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.punche.centroreferencia.dtos.CentroRefereciaCreateRequestDto;
import microservice.shared_data.entities.Departamento;
import microservice.shared_data.entities.Distrito;
import microservice.shared_data.entities.DivisionTerritorialEntity;
import microservice.shared_data.entities.Provincia;
import oracle.jdbc.driver.OracleConnection;

@Repository
@AllArgsConstructor
@Log4j2
public class CentroReferenciaRepository {

   private final JdbcTemplate jdbcTemplate;
   private final DataSource dataSource;

   public List<Departamento> findAllDepartamentos() {

      final String RS_CURSOR_PARAM = "c_resultado_busqueda";

      SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
      jdbcCall.withProcedureName("USP_LISTAR_DEPARTAMENTOS");
      jdbcCall.returningResultSet(RS_CURSOR_PARAM, BeanPropertyRowMapper.newInstance(Departamento.class));

      Map<String, Object> outParams = jdbcCall.execute();

      return (List<Departamento>) outParams.get(RS_CURSOR_PARAM);

   }

   public List<Provincia> findAllProvinciasPorDepartamento(String idUbigeo) {

      final String RS_CURSOR_PARAM = "c_resultado_busqueda";
      final String ID_UBIGEO_PARAM = "p_ubigeo_dep";

      SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
      jdbcCall.withProcedureName("USP_BUSCAR_PROVINCIA_POR_DEPARTAMENTO");
      jdbcCall.declareParameters(new SqlParameter(ID_UBIGEO_PARAM, Types.VARCHAR));
      jdbcCall.returningResultSet(RS_CURSOR_PARAM, BeanPropertyRowMapper.newInstance(Provincia.class));

      Map<String, Object> inParams = new HashMap<>();
      inParams.put(ID_UBIGEO_PARAM, idUbigeo);

      Map<String, Object> outParams = jdbcCall.execute(inParams);

      return (List<Provincia>) outParams.get(RS_CURSOR_PARAM);

   }

   public List<Distrito> findAllDistritosPorProvincia(String idUbigeo) {

      final String RS_CURSOR_PARAM = "c_resultado_busqueda";
      final String ID_UBIGEO_PARAM = "p_ubigeo_prov";

      SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
      jdbcCall.withProcedureName("USP_BUSCAR_DISTRITO_POR_PROVINCIA");
      jdbcCall.declareParameters(new SqlParameter(ID_UBIGEO_PARAM, Types.VARCHAR));
      jdbcCall.returningResultSet(RS_CURSOR_PARAM, BeanPropertyRowMapper.newInstance(Distrito.class));

      Map<String, Object> inParams = new HashMap<>();
      inParams.put(ID_UBIGEO_PARAM, idUbigeo);

      Map<String, Object> outParams = jdbcCall.execute(inParams);

      return (List<Distrito>) outParams.get(RS_CURSOR_PARAM);

   }

   public void saveCentrolReferencia(int tipoReferencia, CentroRefereciaCreateRequestDto centroRefDto) {

      Object[] centroRefValues = new Object[] {
            centroRefDto.getNombre(),
            centroRefDto.getRuc(),
            centroRefDto.getRepresentante(),
            centroRefDto.getDireccion(),
            centroRefDto.getReferencia(),
            centroRefDto.getTelefono(),
            centroRefDto.getCorreo(),
            centroRefDto.getUsuRegistra(),
            centroRefDto.getUbigeo(),
            centroRefDto.getContacto()
      };

      try (OracleConnection conn = dataSource.getConnection().unwrap(OracleConnection.class)) {

         Struct centroRefParam = conn.createStruct("O_CENTRO_REFERENCIA", centroRefValues);

         SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
         jdbcCall.withProcedureName("USP_GUARDAR_CENTRO_REFERENCIA");

         Map<String, Object> inParams = new HashMap<>();
         inParams.put("p_tipo_ref", tipoReferencia);
         inParams.put("p_centro_ref", centroRefParam);
         jdbcCall.execute(inParams);

      } catch (SQLException e) {
         log.error("Error al guardar el centro de referencia: {}", e.getMessage());
      }

   }

   public List<DivisionTerritorialEntity> findDivisionTerritorialByUbigeo(String idUbigeo) {

      final String RS_CURSOR_PARAM = "c_resultado_busqueda";

      SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate);
      jdbcCall.withProcedureName("USP_BUSCAR_DIVICION_TERRITORIAL_POR_UBIGEO");
      jdbcCall.returningResultSet(RS_CURSOR_PARAM, BeanPropertyRowMapper.newInstance(DivisionTerritorialEntity.class));

      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_ubigeo", idUbigeo);

      Map<String, Object> outParams = jdbcCall.execute(inParams);

      return (List<DivisionTerritorialEntity>) outParams.get(RS_CURSOR_PARAM);
   }

}
