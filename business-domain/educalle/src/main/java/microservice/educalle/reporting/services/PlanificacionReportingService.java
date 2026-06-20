package microservice.educalle.reporting.services;

import microservice.educalle.zona.dtos.ZonaIntervencionParamsDto;
import net.sf.jasperreports.engine.JRException;

public interface PlanificacionReportingService {

   byte[] generateCompromisoFamiliarAsPdf(Long idFamilia) throws JRException;

   byte[] generateZonaIntervencionExcelReportByParams(ZonaIntervencionParamsDto params);

}