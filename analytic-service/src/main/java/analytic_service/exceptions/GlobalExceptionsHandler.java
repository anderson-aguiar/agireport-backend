package analytic_service.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException exception){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }
    @ExceptionHandler(ServiceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleServiceNotFound(ServiceNotFoundException exception){
        HttpStatus status = HttpStatus.SERVICE_UNAVAILABLE;
        ErrorResponse errorResponse = new ErrorResponse(status.value(), status.getReasonPhrase(), exception.getMessage());
        return new ResponseEntity<>(errorResponse, status);
    }

}
