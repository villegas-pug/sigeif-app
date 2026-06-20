package microservice.educalle.anexorespuesta.exceptionhandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import microservice.shared_data.dtos.responses.ApiResponse;
import microservice.shared_data.enums.ApiResponseStatus;
import microservice.educalle.anexorespuesta.exceptions.PersonalInvalidException;

@RestControllerAdvice
public class ValidaAnexoCabeceraExceptionHandler {

      @ExceptionHandler(value = { PersonalInvalidException.class })
      public ResponseEntity<?> handleNotFoundException(Exception e) {

            ApiResponseStatus apiStatus = ApiResponseStatus.AUTH_ERROR;
            return ResponseEntity
                        .status(apiStatus.getCode())
                        .body(
                                    ApiResponse
                                                .builder()
                                                .message(apiStatus.getMessage())
                                                .code(apiStatus.getCode())
                                                .build());

      }

}
