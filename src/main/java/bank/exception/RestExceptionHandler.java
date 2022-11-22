package bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleServiceException(final ServiceException ex, final WebRequest request) {
        return ResponseEntity.status(ex.getCode()).body(new ServiceExceptionDTO(ex));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleServiceException(final MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ServiceExceptionDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }
}
