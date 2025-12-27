package microservice.scheduling.shared.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import lombok.extern.log4j.Log4j2;
import microservice.shared_data.services.BaseApachePOIReportingService;

@Log4j2
public abstract class BaseReportingService extends BaseApachePOIReportingService {

   protected void generateExcelFileAndSave(List<Map<String, Object>> dataSet) throws IOException {

      // * 1. Crea sufijo para nombre de archivo

      // * 2. Crea archivo
      byte[] file = super.generateDynamicExcelFile("░", dataSet);

      // * 3. Crea directorio
      Path path = Paths.get(this.getBaseOutputPath());
      log.info(path);
      if (!Files.exists(path)) {
         Files.createDirectories(path);
      }

      // * 4. Guarda archivo
      Path fullPath = Paths
            .get(this.getBaseOutputPath().concat(this.getFileName()).concat(".xlsx"));
      Files.write(fullPath, file);

   }

   protected LocalDateTime getDateTime() {
      return LocalDateTime.now();
   }

   protected String getDateTimeFormat() {
      return LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMYYYY_HHmmss"));
   }

   protected abstract String getBaseOutputPath();

   protected abstract String getFileName();

}
