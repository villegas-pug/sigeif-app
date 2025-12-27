package microservice.cedif.domain.ports.in.reporting;

import java.util.List;
import java.util.Map;

public interface CommonReportingServicePort {

   public byte[] generateDynamicExcelFile(String sheetName, List<Map<String, Object>> data);

}
