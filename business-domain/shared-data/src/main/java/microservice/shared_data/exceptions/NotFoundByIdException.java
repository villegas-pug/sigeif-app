package microservice.shared_data.exceptions;

public class NotFoundByIdException extends RuntimeException {

   public NotFoundByIdException(Long id) {
      super(id.toString());
   }

}
