package microservice.cedif.infrastructure.adapters.in.web.controllers.reporting;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.cedif.domain.ports.in.reporting.IntervencionReportingServicePort;
import microservice.shared_data.controller.BaseRestController;
import net.sf.jasperreports.engine.JRException;

@RestController
@AllArgsConstructor
public class IntervencionReportingController extends BaseRestController {

   private final IntervencionReportingServicePort service;

   @GetMapping(path = { "/generateFichaEgresoCedifPdf" })
   public ResponseEntity<byte[]> generateFichaEgresoCedifPdf(@RequestParam Long idIntegrante)
         throws JRException {
      byte[] reporting = this.service.generateFichaEgresoCedifPdf(idIntegrante);
      return super.buildDownloadResponseEntity("ficha_egreso_del_cedif.pdf", reporting);
   }

}
