package microservice.scheduling.punche.schedulers;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import microservice.scheduling.punche.domain.GenerateFFSILUseCase;
import microservice.scheduling.punche.properties.FFSILProperties;
import microservice.scheduling.shared.scheduler.BaseScheduler;

@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "schedulers.punche.ffsil.report.excel.enabled", havingValue = "true", matchIfMissing = false)
public class GenerateFFSILScheduler extends BaseScheduler implements CommandLineRunner {

   private final GenerateFFSILUseCase useCase;
   private final FFSILProperties properties;

   @Scheduled(cron = "${schedulers.punche.ffsil.report.excel.cron}")
   private void generateFFSILReport() throws Exception {
      super.logInicioTarea(this.properties.getExcel().getFileName());
      this.useCase.generateFFSILReport();
      super.logFinTarea(this.properties.getExcel().getFileName());
   }

   @Override
   public void run(String... args) throws Exception {
      // this.generateFFSILReport();
   }

}
