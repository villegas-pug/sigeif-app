package microservice.punche.reporting.services;

import net.sf.jasperreports.engine.JRException;

public interface EjecucionReportingService {

   byte[] generatePatfamAsPdf() throws JRException;

}
