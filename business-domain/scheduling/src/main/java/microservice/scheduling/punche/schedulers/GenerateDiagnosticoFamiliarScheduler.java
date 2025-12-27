package microservice.scheduling.punche.schedulers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import microservice.scheduling.punche.domain.GenerateDiagnosticoFamiliarUseCase;
import microservice.scheduling.punche.properties.DiagnosticoFamiliarProperties;
import microservice.scheduling.shared.scheduler.BaseScheduler;

@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "schedulers.punche.diagnostico-familiar.report.excel.enabled", havingValue = "true", matchIfMissing = false)
public class GenerateDiagnosticoFamiliarScheduler extends BaseScheduler implements CommandLineRunner {

   private final GenerateDiagnosticoFamiliarUseCase useCase;
   private final DiagnosticoFamiliarProperties properties;

   @Scheduled(cron = "${schedulers.punche.diagnostico-familiar.report.excel.cron}")
   private void generateDiagnosticoFamiliarReport() throws Exception {
      super.logInicioTarea(this.properties.getExcel().getFileName());
      this.useCase.generateDiagnosticoFamiliarReport();
      super.logFinTarea(this.properties.getExcel().getFileName());
   }

   @Override
   public void run(String... args) throws Exception {
      // this.generateDiagnosticoFamiliarReport();
   }

}
