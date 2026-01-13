package microservice.scheduling.punche.infrastructure;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import microservice.scheduling.punche.domain.GenerateMatrizUseCase;
import microservice.scheduling.punche.exceptions.IntervencionEstrategicaNotFoundException;
import microservice.scheduling.punche.properties.MatrizProperties;
import microservice.scheduling.shared.repository.BaseRepository;
import microservice.scheduling.shared.service.BaseReportingService;

@Service
@AllArgsConstructor
public class GenerateMatrizUseCaseImpl extends BaseReportingService implements GenerateMatrizUseCase {

   private final MatrizProperties properties;
   private final BaseRepository repository;

   @Override
   public void generateMatrizReport() throws IOException {
      // * 1. Ejecutar procedimiento almacenado
      List<Map<String, Object>> dataset = this.repository.executeProcedureAndFetchResult(
            "USP_GENERAR_REPORTE_MATRIZ_FAMILIAS_IGUALITARIAS", null, "p_resultado");

      if (dataset.isEmpty()) {
         throw new IntervencionEstrategicaNotFoundException();
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
