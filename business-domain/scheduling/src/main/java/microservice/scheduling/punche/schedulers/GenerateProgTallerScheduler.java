package microservice.scheduling.punche.schedulers;

import java.io.IOException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.scheduling.punche.domain.GenerateProgTallerUseCase;
import microservice.scheduling.punche.properties.ProgTallerProperties;
import microservice.scheduling.shared.scheduler.BaseScheduler;

@Component
@AllArgsConstructor
@Log4j2
@ConditionalOnProperty(value = "schedulers.punche.prog-taller.report.excel.enabled", havingValue = "true", matchIfMissing = false)
public class GenerateProgTallerScheduler extends BaseScheduler implements CommandLineRunner {

   private final GenerateProgTallerUseCase useCase;
   private final ProgTallerProperties properties;

   @Scheduled(cron = "${schedulers.punche.prog-taller.report.excel.cron}")
   private void generateProgTallerReport() throws IOException {
      super.logInicioTarea(this.properties.getExcel().getFileName());
      this.useCase.generateProgTallerReport();
      super.logFinTarea(this.properties.getExcel().getFileName());
   }

   @Override
   public void run(String... args) throws Exception {
      this.generateProgTallerReport();
   }

}
