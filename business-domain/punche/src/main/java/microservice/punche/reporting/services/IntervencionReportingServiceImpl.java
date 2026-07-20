package microservice.punche.reporting.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import microservice.punche.reporting.repositories.IntervencionReportingRepository;
import microservice.shared_data.exceptions.NotFoundException;
import microservice.shared_data.services.BaseReportingService;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class IntervencionReportingServiceImpl extends BaseReportingService
            implements IntervencionReportingService {

    private final IntervencionReportingRepository repository;

    @Override
    public byte[] generateSesionesExcelReport(LocalDate fechaIni, LocalDate fechaFin) {
        List<Map<String, Object>> dataset = this.repository.executeSesionesListar(fechaIni, fechaFin);
        if (dataset == null || dataset.isEmpty()) {
            throw new NotFoundException();
        }

        return super.apachePOIReportingService.generateDynamicExcelFile("░", dataset);
    }

    @Override
    public byte[] generateTalleresFamiliasExcelReport(LocalDate fechaIni, LocalDate fechaFin) {
        List<Map<String, Object>> dataset = this.repository.executeTalleresFamiliasListar(fechaIni, fechaFin);
        if (dataset == null || dataset.isEmpty()) {
            throw new NotFoundException();
        }

        return super.apachePOIReportingService.generateDynamicExcelFile("░", dataset);
    }

    @Override
    protected String getBaseTemplatePath() {
        return "/templates/4.reporting/";
    }

}
