package microservice.scheduling.punche.schedulers;

import java.io.IOException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.scheduling.punche.domain.GenerateMatrizUseCase;
import microservice.scheduling.punche.properties.MatrizProperties;
import microservice.scheduling.shared.scheduler.BaseScheduler;

@Component
@AllArgsConstructor
@Log4j2
@ConditionalOnProperty(value = "schedulers.punche.matriz.report.excel.enabled", havingValue = "true", matchIfMissing = false)
public class GenerateMatrizScheduler extends BaseScheduler implements CommandLineRunner {

   private final GenerateMatrizUseCase useCase;
   private final MatrizProperties properties;

   @Scheduled(cron = "${schedulers.punche.intervenciones-estrategicas.report.excel.cron}")
   void generateMatrizReport() throws IOException {
      super.logInicioTarea(this.properties.getExcel().getFileName());
      this.useCase.generateMatrizReport();
      super.logFinTarea(this.properties.getExcel().getFileName());
   }

   @Override
   public void run(String... args) throws Exception {
      this.generateMatrizReport();
   }

}
