package microservice.scheduling.shared.scheduler;

import java.time.LocalDateTime;

import lombok.extern.log4j.Log4j2;

@Log4j2
public abstract class BaseScheduler {

   private static final String INICIO_TAREA_MSG = ">>>>> Iniciando tarea programada: `{}` (inicio: {}).";
   private static final String FIN_TAREA_MSG = "<<<<< Finalizada tarea porgramada: `{}` (Fin: {}).";

   protected void logInicioTarea(String tarea) {
      log.info(INICIO_TAREA_MSG, tarea, LocalDateTime.now());
   }

   protected void logFinTarea(String tarea) {
      log.info(FIN_TAREA_MSG, tarea, LocalDateTime.now());
   }

}
