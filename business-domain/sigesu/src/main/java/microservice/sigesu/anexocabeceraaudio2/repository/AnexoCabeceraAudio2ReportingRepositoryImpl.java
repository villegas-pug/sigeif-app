package microservice.sigesu.anexocabeceraaudio2.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import microservice.shared_data.repositories.BaseOracleRepository;

@Repository
public class AnexoCabeceraAudio2ReportingRepositoryImpl extends BaseOracleRepository
		implements AnexoCabeceraAudio2ReportingRepository {

	public AnexoCabeceraAudio2ReportingRepositoryImpl(JdbcTemplate jdbcTemplate, DataSource dataSource) {
		super(jdbcTemplate, dataSource);
	}

	@Override
	public List<Map<String, Object>> listarCodigosFamilias(Long idServicio, Long idFamilia, String codigo) {
		Map<String, Object> inParams = new HashMap<>();
		inParams.put("p_si_id_servicio", idServicio);
		inParams.put("p_pf_id_familia", idFamilia);
		inParams.put("p_codigo", codigo);
		return super.executeProcedureAndFetchResult("PRC_SSI_CODIGOS_FAMILIAS_LISTAR", inParams, "p_cursor_out");
	}

}