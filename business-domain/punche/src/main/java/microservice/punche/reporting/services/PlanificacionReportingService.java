package microservice.punche.reporting.services;

import microservice.punche.zona.dtos.ZonaIntervencionParamsDto;
import net.sf.jasperreports.engine.JRException;

public interface PlanificacionReportingService {

   byte[] generateCompromisoFamiliarAsPdf(Long idFamilia) throws JRException;

   byte[] generateZonaIntervencionExcelReportByParams(ZonaIntervencionParamsDto params);

}