package microservice.scheduling.siges.schedulers;

import java.io.IOException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import microservice.scheduling.siges.domain.GenerateMatrizFichasSigeUseCase;
import microservice.scheduling.siges.properties.MatrizFichasSigeProperties;
import microservice.scheduling.shared.scheduler.BaseScheduler;

@Log4j2
@Component
@AllArgsConstructor
@ConditionalOnProperty(value = "schedulers.siges.matriz_fichas_siges.report.excel.enabled", havingValue = "true", matchIfMissing = false)
public class GenerateMatrizFichasSigeScheduler
      extends BaseScheduler
      implements CommandLineRunner {

   private final GenerateMatrizFichasSigeUseCase useCase;
   private final MatrizFichasSigeProperties properties;

   @Scheduled(cron = "${schedulers.siges.matriz_fichas_siges.report.excel.cron}")
   void generateMatrizFichasSigeReport() throws IOException {
      super.logInicioTarea(this.properties.getExcel().getFileName());
      this.useCase.generateMatrizFichasSigeReport();
      super.logFinTarea(this.properties.getExcel().getFileName());
   }

   @Override
   public void run(String... args) throws Exception {
      // * Descomentar solo para ejecuciones manuales en dev:
      this.generateMatrizFichasSigeReport();
   }

}
