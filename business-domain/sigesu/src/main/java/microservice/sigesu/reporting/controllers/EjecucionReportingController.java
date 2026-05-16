package microservice.sigesu.reporting.controllers;

import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.sigesu.reporting.services.EjecucionReportingService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@AllArgsConstructor
public class EjecucionReportingController {

   private final EjecucionReportingService service;

   @GetMapping(path = { "/generatePatfamAsPdf" })
   public ResponseEntity<byte[]> generatePatfamAsPdf() throws JRException {

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_PDF);
      headers.setContentDisposition(
            ContentDisposition.builder("attachment").filename("patfam.pdf").build());

      return ResponseEntity.ok().headers(headers).body(this.service.generatePatfamAsPdf());
   }

}
