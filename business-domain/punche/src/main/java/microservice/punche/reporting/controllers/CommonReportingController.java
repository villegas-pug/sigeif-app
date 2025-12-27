package microservice.punche.reporting.controllers;

import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import microservice.punche.reporting.services.CommonReportingService;
import microservice.shared_data.controller.BaseRestController;

@RestController
@AllArgsConstructor
public class CommonReportingController extends BaseRestController {

   private final CommonReportingService service;

   @PostMapping(path = { "/generateDynamicExcelReport" })
   public ResponseEntity<byte[]> generateDynamicExcelReport(@RequestParam String fileName,
         @RequestBody List<Map<String, Object>> dataFile) {

      byte[] reporting = this.service.apachePOIReportingService.generateDynamicExcelFile("░", dataFile);

      return super.buildDownloadResponseEntity(fileName.concat(".xlsx"), reporting);
   }

}
