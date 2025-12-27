package microservice.shared_data.exceptions;

public class JsonConversionException extends RuntimeException {

   public JsonConversionException(String msj, Throwable cause) {
      super(msj, cause);
   }

}
