package github.abhiace.ecomstore.orderservice.exception;

import lombok.Getter;

@Getter
public class InvalidOrderException extends RuntimeException {

    private String errorCode;
    public InvalidOrderException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
