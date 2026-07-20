package microservice.scheduling.punche.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class ProgTallerNotFoundException extends RuntimeException {

   private static final String DEFAULT_MESSAGE = "¡Datos para el reporte de Programacion de Talleres no encontrados!";

   public ProgTallerNotFoundException() {
      super(DEFAULT_MESSAGE);
      log.error(DEFAULT_MESSAGE);
   }

}
