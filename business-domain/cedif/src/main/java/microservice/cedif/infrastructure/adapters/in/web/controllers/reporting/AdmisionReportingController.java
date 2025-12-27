package microservice.cedif.infrastructure.adapters.in.web.controllers.reporting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.ports.in.reporting.AdmisionReportingServicePort;
import microservice.shared_data.controller.BaseRestController;
import net.sf.jasperreports.engine.JRException;

@RestController
@AllArgsConstructor
public class AdmisionReportingController extends BaseRestController {

   private final AdmisionReportingServicePort service;

   @GetMapping(path = { "/generateFichaCompromisoResponsablePdf" })
   public ResponseEntity<byte[]> generateFichaCompromisoResponsablePdf(@RequestParam Long idIntegrante)
         throws JRException {
      byte[] reporting = this.service.generateFichaCompromisoResponsablePdf(idIntegrante);
      return super.buildDownloadResponseEntity("ficha_compromiso_responsable.pdf", reporting);
   }

   @GetMapping(path = { "/generateFichaAutorizacionIngresoNNAPdf" })
   public ResponseEntity<byte[]> generateFichaAutorizacionIngresoNNAPdf(@RequestParam Long idIntegrante)
         throws JRException {
      byte[] reporting = this.service.generateFichaAutorizacionIngresoNNAPdf(idIntegrante);
      return super.buildDownloadResponseEntity("ficha_autorizacion_ingreso_nna_y_adolecente.pdf", reporting);
   }

}
