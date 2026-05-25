package microservice.sigesu.anexocabeceraaudio2.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import microservice.sigesu.anexocabeceraaudio2.repository.AnexoCabeceraAudio2ReportingRepository;
import microservice.shared_data.exceptions.NotFoundException;
import microservice.shared_data.services.BaseReportingService;

@Service
@AllArgsConstructor
public class AnexoCabeceraAudio2ReportingServiceImpl extends BaseReportingService
		implements AnexoCabeceraAudio2ReportingService {

	private final AnexoCabeceraAudio2ReportingRepository reportingRepository;

	@Override
	public List<Map<String, Object>> listarCodigosFamilias(Long idServicio, Long idFamilia, String codigo) {
		return this.reportingRepository.listarCodigosFamilias(idServicio, idFamilia, codigo);
	}

	@Override
	public byte[] generateCodigosFamiliasExcelReport(Long idServicio, Long idFamilia, String codigo) {
		List<Map<String, Object>> dataset = this.listarCodigosFamilias(idServicio, idFamilia, codigo);
		if (dataset == null || dataset.isEmpty()) {
			throw new NotFoundException();
		}
		return super.apachePOIReportingService.generateDynamicExcelFile("Reporte_Codigos_Familias", dataset);
	}

	@Override
	protected String getBaseTemplatePath() {
		return "";
	}

}