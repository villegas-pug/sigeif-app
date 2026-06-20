package microservice.educalle.anexocabeceraaudio2.repository;

import java.util.List;
import java.util.Map;

public interface AnexoCabeceraAudio2ReportingRepository {

	List<Map<String, Object>> listarCodigosFamilias(Long idServicio, Long idFamilia, String codigo);

}