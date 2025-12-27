package microservice.scheduling.punche.infrastructure;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.scheduling.punche.domain.GenerateIntervencionEstrategicaUseCase;
import microservice.scheduling.punche.exceptions.IntervencionEstrategicaNotFoundException;
import microservice.scheduling.punche.properties.IntervencionEstrategicaProperties;
import microservice.scheduling.shared.repository.BaseRepository;
import microservice.scheduling.shared.service.BaseReportingService;

@Service
@AllArgsConstructor
public class GenerateIntervencionEstrategicaUseCaseImpl extends BaseReportingService
      implements GenerateIntervencionEstrategicaUseCase {

   private final IntervencionEstrategicaProperties properties;
   private final BaseRepository repository;

   @Override
   public void generateIntervencionEstrategicaReport() throws IOException {

      // * 1. Ejecutar procedimiento almacenado
      List<Map<String, Object>> dataset = this.repository.executeProcedureAndFetchResult(
            "USP_GENERAR_REPORTE_INTERVENCIONES_ESTRATEGICA_FAMILIAS_IGUALITARIAS", null, "p_resultado");

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
