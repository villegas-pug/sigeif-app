package microservice.sigesu.anexo.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import microservice.sigesu.anexo.dtos.AnexoDto;
import microservice.sigesu.anexo.dtos.AnexoSimpleDto;
import microservice.sigesu.anexo.dtos.UnidadDto;
import microservice.sigesu.anexo.dtos.UnidadServicioDto;
import microservice.sigesu.anexo.mappers.AnexoSimpleRowMapper;
import microservice.sigesu.anexo.mappers.UnidadServicioRowMapper;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class AnexoRepositoryImpl extends BaseOracleRepository implements AnexoRepository {

    public AnexoRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        super(jdbcTemplate, dataSource);
    }

    @Override
    public List<AnexoDto> listarAnexosPorFiltro(
            Long idUnidadOrganica,
            Long idServicio,
            Long idAnexo) {

        Map<String, Object> inParams = new HashMap<>();

        inParams.put("P_ID_UNIDAD_ORGANICA", idUnidadOrganica);
        inParams.put("P_ID_SERVICIO", idServicio);
        inParams.put("P_ID_ANEXO", idAnexo);

        return this.executeProcedureWithInParams(
                "USP_LISTAR_ANEXOS_POR_FILTRO",
                inParams,
                "C_RESULTADO",
                AnexoDto.class);

    }

    @Override
    public List<UnidadServicioDto> listarServiciosPorUnidad(Long idUnidadOrganica) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("USP_LISTAR_UNIDADES_SERVICIOS")
                .returningResultSet("C_RESULTADO", new UnidadServicioRowMapper());

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_UNIDAD_ORGANICA", idUnidadOrganica);

        Map<String, Object> result = jdbcCall.execute(params);

        return (List<UnidadServicioDto>) result.get("C_RESULTADO");
    }

    // 🔵 ANEXOS POR SERVICIO

    @Override
    public List<AnexoSimpleDto> listarAnexosPorServicio(Long idUnidadOrganica, Long idServicio) {

        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("SP_LISTAR_ANEXOS_POR_SERVICIO")
                .returningResultSet("P_CURSOR", new AnexoSimpleRowMapper()); // <<--- usar P_CURSOR

        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_UNIDAD_ORGANICA", idUnidadOrganica);
        params.put("P_ID_SERVICIO", idServicio);

        Map<String, Object> result = jdbcCall.execute(params);

        return (List<AnexoSimpleDto>) result.get("P_CURSOR");
    }

    @Override
    public List<UnidadDto> listarUnidades() {

        Map<String, Object> inParams = new HashMap<>();

        return this.executeProcedureWithInParams(
                "USP_LISTAR_UNIDADES",
                inParams,
                "C_RESULTADO",
                UnidadDto.class);
    }

}
