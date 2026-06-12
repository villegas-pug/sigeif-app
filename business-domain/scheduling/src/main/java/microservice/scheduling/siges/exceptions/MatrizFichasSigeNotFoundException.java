package microservice.scheduling.siges.exceptions;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MatrizFichasSigeNotFoundException extends RuntimeException {

   private static final String DEFAULT_MESSAGE =
         "¡Datos para el reporte MatrizFichasSige no encontrados!";

   public MatrizFichasSigeNotFoundException() {
      super(DEFAULT_MESSAGE);
      log.error(DEFAULT_MESSAGE);
   }

}
