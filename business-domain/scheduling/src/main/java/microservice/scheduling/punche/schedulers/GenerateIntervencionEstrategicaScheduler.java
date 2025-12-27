package microservice.scheduling.punche.schedulers;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.scheduling.punche.domain.GenerateIntervencionEstrategicaUseCase;
import microservice.scheduling.punche.properties.IntervencionEstrategicaProperties;
import microservice.scheduling.shared.scheduler.BaseScheduler;

@Component
@AllArgsConstructor
@Log4j2
@ConditionalOnProperty(value = "schedulers.punche.intervenciones-estrategicas.report.excel.enabled", havingValue = "true", matchIfMissing = false)
public class GenerateIntervencionEstrategicaScheduler extends BaseScheduler implements CommandLineRunner {

   private final GenerateIntervencionEstrategicaUseCase useCase;
   private final IntervencionEstrategicaProperties properties;

   @Scheduled(cron = "${schedulers.punche.intervenciones-estrategicas.report.excel.cron}")
   void generateIntervencionEstrategicaReport() throws IOException {
      super.logInicioTarea(this.properties.getExcel().getFileName());
      this.useCase.generateIntervencionEstrategicaReport();
      super.logFinTarea(this.properties.getExcel().getFileName());
   }

   @Override
   public void run(String... args) throws Exception {
      // this.generateIntervencionEstrategicaReport();
   }

}
