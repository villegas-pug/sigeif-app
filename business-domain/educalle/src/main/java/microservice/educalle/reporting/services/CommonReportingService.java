package microservice.educalle.reporting.services;

import org.springframework.stereotype.Service;
import microservice.shared_data.services.BaseReportingService;

@Service
public class CommonReportingService extends BaseReportingService {

   @Override
   protected String getBaseTemplatePath() {
      return "";
   }

}
