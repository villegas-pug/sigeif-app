package microservice.cedif.domain.ports.in.reporting;

import net.sf.jasperreports.engine.JRException;

public interface AdmisionReportingServicePort {

   byte[] generateFichaCompromisoResponsablePdf(Long idIntegrante) throws JRException;

   byte[] generateFichaAutorizacionIngresoNNAPdf(Long idIntegrante) throws JRException;

}
