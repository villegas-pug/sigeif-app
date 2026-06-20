package microservice.educalle.reporting.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import microservice.educalle.pais.service.PaisService;
import microservice.educalle.reporting.dtos.PatfamReportingDto;
import microservice.shared_data.entities.PaisEntity;
import microservice.shared_data.services.BaseReportingService;
import net.sf.jasperreports.engine.JRException;

@Service
@AllArgsConstructor
public class EjecucionJasperReportingServiceImpl extends BaseReportingService
      implements EjecucionReportingService {

   private final PaisService paisService;

   @Override
   @Transactional(readOnly = true)
   public byte[] generatePatfamAsPdf() throws JRException {

      List<PaisEntity> models = this.paisService.findAllPais();

      // * Parametros

      List<PatfamReportingDto> patFams = models.stream().map(pais -> {
         return PatfamReportingDto.builder().codFamilia(pais.getNacionalidad()).referencia(pais.getNombre()).build();
      }).toList();

      Map<String, Object> parameters = new HashMap<>();
      parameters.put("dsPatfam", super.jasperReportingService.createDataSource(patFams));

      // * Exportar a PDF
      return super.jasperReportingService.generatePdfReport("rpt_plan_integral_trabajo_familiar_patfam.jrxml",
            parameters);
   }

   @Override
   protected String getBaseTemplatePath() {
      return "/templates/1.ejecucion/";
   }

}
