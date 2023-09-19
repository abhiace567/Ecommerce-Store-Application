package github.abhiace.ecomstore.paymentservice.exception;

import github.abhiace.ecomstore.paymentservice.model.ErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PaymentInfoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientStockException(PaymentInfoNotFoundException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), exception.getErrorCode()),
                HttpStatus.NOT_FOUND);
    }
}
