package microservice.scheduling.punche.infrastructure;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import microservice.scheduling.punche.domain.GenerateDiagnosticoFamiliarUseCase;
import microservice.scheduling.punche.properties.DiagnosticoFamiliarProperties;
import microservice.scheduling.shared.repository.BaseRepository;
import microservice.scheduling.shared.service.BaseReportingService;
import microservice.shared_data.enums.InabifServices;
import microservice.shared_data.enums.PuncheFichas;

@Service
@AllArgsConstructor
public class GenerateDiagnosticoFamiliarUseCaseImpl extends BaseReportingService
      implements GenerateDiagnosticoFamiliarUseCase {

   private final DiagnosticoFamiliarProperties properties;
   private final BaseRepository repository;

   @Override
   public void generateDiagnosticoFamiliarReport() throws IOException {

      // * 1. Ejecutar procedimiento almacenado
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_Servicio", InabifServices.PUNCHE.getId());
      inParams.put("p_num_anexo", PuncheFichas.DIAGNOSTICO_FAMILIAR.getId());

      List<Map<String, Object>> dataset = this.repository
            .executeProcedureAndFetchResult("USP_GENERAR_REPORTE_FICHA_PARAMETRIZADO", inParams, "p_resultado");

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
