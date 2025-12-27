package microservice.scheduling.punche.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class IntervencionEstrategicaNotFoundException extends RuntimeException {

   private static final String DEFAULT_MESSAGE = "¡Datos para el reporte de Intervencion Estrategica no encontrados!";

   public IntervencionEstrategicaNotFoundException() {
      super(DEFAULT_MESSAGE);
      log.error(DEFAULT_MESSAGE);
   }

}
