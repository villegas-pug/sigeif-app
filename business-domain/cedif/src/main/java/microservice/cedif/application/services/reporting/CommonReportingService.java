package microservice.cedif.application.services.reporting;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import microservice.cedif.domain.ports.in.reporting.CommonReportingServicePort;
import microservice.shared_data.services.BaseReportingService;

@Service
public class CommonReportingService extends BaseReportingService implements CommonReportingServicePort {

   @Override
   @Transactional(readOnly = true)
   public byte[] generateDynamicExcelFile(String sheetName, List<Map<String, Object>> data) {
      return super.apachePOIReportingService.generateDynamicExcelFile(sheetName, data);
   }

   @Override
   protected String getBaseTemplatePath() {
      return "";
   }

}
