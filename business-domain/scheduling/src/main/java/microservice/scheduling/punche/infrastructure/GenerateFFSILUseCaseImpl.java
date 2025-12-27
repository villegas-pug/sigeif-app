package microservice.scheduling.punche.infrastructure;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;
import microservice.scheduling.punche.domain.GenerateFFSILUseCase;
import microservice.scheduling.punche.properties.FFSILProperties;
import microservice.scheduling.shared.repository.BaseRepository;
import microservice.scheduling.shared.service.BaseReportingService;
import microservice.shared_data.enums.InabifServices;
import microservice.shared_data.enums.PuncheFichas;

@Service
@AllArgsConstructor
public class GenerateFFSILUseCaseImpl extends BaseReportingService
      implements GenerateFFSILUseCase {

   private final FFSILProperties properties;
   private final BaseRepository repository;

   @Override
   public void generateFFSILReport() throws IOException {

      // * 1. Ejecutar procedimiento almacenado
      Map<String, Object> inParams = new HashMap<>();
      inParams.put("p_id_Servicio", InabifServices.PUNCHE.getId());
      inParams.put("p_num_anexo", PuncheFichas.FFSIL.getId());

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
