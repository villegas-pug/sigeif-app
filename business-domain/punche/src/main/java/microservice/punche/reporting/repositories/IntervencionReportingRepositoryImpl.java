package microservice.punche.reporting.repositories;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class IntervencionReportingRepositoryImpl extends BaseOracleRepository
        implements IntervencionReportingRepository {

    public IntervencionReportingRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
        super(jdbcTemplate, dataSource);
    }

    private static final String CURSOR_OUT = "p_cursor_out";

    @Override
    public List<Map<String, Object>> executeSesionesListar(LocalDate fechaIni, LocalDate fechaFin, Long idZona) {
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_fecha_ini", fechaIni != null ? Date.valueOf(fechaIni) : null);
        inParams.put("p_fecha_fin", fechaFin != null ? Date.valueOf(fechaFin) : null);
        inParams.put("p_id_zona", idZona);

        return super.executeProcedureAndFetchResult("PRC_PUNCHE_SESIONES_LISTAR", inParams, CURSOR_OUT);
    }

    @Override
    public List<Map<String, Object>> executeTalleresFamiliasListar(LocalDate fechaIni, LocalDate fechaFin, Long idZona) {
        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_fecha_ini", fechaIni != null ? Date.valueOf(fechaIni) : null);
        inParams.put("p_fecha_fin", fechaFin != null ? Date.valueOf(fechaFin) : null);
        inParams.put("p_id_zona", idZona);

        return super.executeProcedureAndFetchResult("PRC_PUNCHE_TALLERES_FAMILIAS_LISTAR", inParams, CURSOR_OUT);
    }

}
