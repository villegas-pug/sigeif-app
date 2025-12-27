package microservice.scheduling.punche.schedulers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import microservice.scheduling.punche.domain.GenerateEncuestaSatisfaccionUseCase;
import microservice.scheduling.punche.properties.EncuestaSatisfaccionProperties;
import microservice.scheduling.shared.scheduler.BaseScheduler;

@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "schedulers.punche.encuesta-satisfaccion.report.excel.enabled", havingValue = "true", matchIfMissing = false)
public class GenerateEncuestaSatisfaccionScheduler extends BaseScheduler implements CommandLineRunner {

   private final GenerateEncuestaSatisfaccionUseCase useCase;
   private final EncuestaSatisfaccionProperties properties;

   @Scheduled(cron = "${schedulers.punche.encuesta-satisfaccion.report.excel.cron}")
   private void generateDiagnosticoFamiliarReport() throws Exception {
      super.logInicioTarea(this.properties.getExcel().getFileName());
      this.useCase.generateEncuestaSatisfaccionReport();
      super.logFinTarea(this.properties.getExcel().getFileName());
   }

   @Override
   public void run(String... args) throws Exception {
      // this.generateDiagnosticoFamiliarReport();
   }

}
