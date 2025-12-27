package microservice.cedif.domain.ports.in.reporting;

import net.sf.jasperreports.engine.JRException;

public interface IntervencionReportingServicePort {

   byte[] generateFichaEgresoCedifPdf(Long idIntegrante) throws JRException;

}
