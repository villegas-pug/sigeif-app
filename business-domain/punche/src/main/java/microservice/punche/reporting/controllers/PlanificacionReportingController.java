package microservice.punche.reporting.controllers;

import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import microservice.punche.reporting.services.PlanificacionReportingService;
import microservice.punche.zona.dtos.ZonaIntervencionParamsDto;
import microservice.shared_data.controller.BaseRestController;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@AllArgsConstructor
public class PlanificacionReportingController extends BaseRestController {

   private final PlanificacionReportingService service;

   @GetMapping(path = { "/generateCompromisoFamiliarPdf" })
   public ResponseEntity<byte[]> generateCompromisoFamiliarPdf(@RequestParam Long idFamilia) throws JRException {

      return super.buildDownloadResponseEntity("compromiso_familiar.pdf",
            this.service.generateCompromisoFamiliarAsPdf(idFamilia));
   }

   @GetMapping(path = { "/generateZonaIntervencionExcelReportByParams" })
   public ResponseEntity<byte[]> generateZonaIntervencionExcelReportByParams(
         @RequestBody @Valid ZonaIntervencionParamsDto params)
         throws JRException {
      return super.buildDownloadResponseEntity("Reporte de zonas de intervención.xlsx",
            this.service.generateZonaIntervencionExcelReportByParams(params));
   }

}
