package microservice.scheduling.punche.schedulers;

import java.io.IOException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.scheduling.punche.domain.GenerateProgSesionUseCase;
import microservice.scheduling.punche.properties.ProgSesionProperties;
import microservice.scheduling.shared.scheduler.BaseScheduler;

@Component
@AllArgsConstructor
@Log4j2
@ConditionalOnProperty(value = "schedulers.punche.prog-sesion.report.excel.enabled", havingValue = "true", matchIfMissing = false)
public class GenerateProgSesionScheduler extends BaseScheduler implements CommandLineRunner {

   private final GenerateProgSesionUseCase useCase;
   private final ProgSesionProperties properties;

   @Scheduled(cron = "${schedulers.punche.prog-sesion.report.excel.cron}")
   private void generateProgSesionReport() throws IOException {
      super.logInicioTarea(this.properties.getExcel().getFileName());
      this.useCase.generateProgSesionReport();
      super.logFinTarea(this.properties.getExcel().getFileName());
   }

   @Override
   public void run(String... args) throws Exception {
      this.generateProgSesionReport();
   }

}
