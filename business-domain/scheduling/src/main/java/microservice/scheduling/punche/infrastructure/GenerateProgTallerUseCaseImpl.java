package microservice.scheduling.punche.infrastructure;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import microservice.scheduling.punche.domain.GenerateProgTallerUseCase;
import microservice.scheduling.punche.exceptions.ProgTallerNotFoundException;
import microservice.scheduling.punche.properties.ProgTallerProperties;
import microservice.scheduling.shared.repository.BaseRepository;
import microservice.scheduling.shared.service.BaseReportingService;

@Service
@AllArgsConstructor
public class GenerateProgTallerUseCaseImpl extends BaseReportingService
      implements GenerateProgTallerUseCase {

   private final ProgTallerProperties properties;
   private final BaseRepository repository;

   @Override
   public void generateProgTallerReport() throws IOException {

      // * 1. Ejecutar procedimiento almacenado (sin IN params; cursor OUT =
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_fecha_ini", null);
      inParams.put("p_fecha_fin", null);
      inParams.put("p_id_zona", -1);
      List<Map<String, Object>> dataset = this.repository.executeProcedureAndFetchResult(
            "PRC_PUNCHE_TALLERES_FAMILIAS_LISTAR", inParams, "p_cursor_out");

      if (dataset.isEmpty()) {
         throw new ProgTallerNotFoundException();
      }

      // * 2. Generar archivo
      super.generateExcelFileAndSave(dataset);
   }

   @Override
   protected String getBaseOutputPath() {
      return this.properties.getExcel().getOutputPath();
   }

   @Override
   protected String getFileName() {
      return this.properties.getExcel().getFileName();
   }

}
