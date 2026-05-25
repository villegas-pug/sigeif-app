package microservice.sigesu.anexocabeceraaudio2.service;

import java.util.List;
import java.util.Map;

public interface AnexoCabeceraAudio2ReportingService {

	List<Map<String, Object>> listarCodigosFamilias(Long idServicio, Long idFamilia, String codigo);

	byte[] generateCodigosFamiliasExcelReport(Long idServicio, Long idFamilia, String codigo);

}