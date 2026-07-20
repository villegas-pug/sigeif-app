package microservice.scheduling.punche.infrastructure;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import microservice.scheduling.punche.domain.GenerateProgSesionUseCase;
import microservice.scheduling.punche.exceptions.ProgSesionNotFoundException;
import microservice.scheduling.punche.properties.ProgSesionProperties;
import microservice.scheduling.shared.repository.BaseRepository;
import microservice.scheduling.shared.service.BaseReportingService;

@Service
@AllArgsConstructor
public class GenerateProgSesionUseCaseImpl extends BaseReportingService
      implements GenerateProgSesionUseCase {

   private final ProgSesionProperties properties;
   private final BaseRepository repository;

   @Override
   public void generateProgSesionReport() throws IOException {

      // * 1. Ejecutar procedimiento almacenado (sin IN params; cursor OUT =
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_fecha_ini", null);
      inParams.put("p_fecha_fin", null);
      List<Map<String, Object>> dataset = this.repository.executeProcedureAndFetchResult(
            "PRC_PUNCHE_SESIONES_LISTAR", inParams, "p_cursor_out");

      if (dataset.isEmpty()) {
         throw new ProgSesionNotFoundException();
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
