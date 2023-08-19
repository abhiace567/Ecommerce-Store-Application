package github.abhiace.ecomstore.orderservice.exception;

import lombok.Getter;

@Getter
public class PlaceOrderException extends RuntimeException {

    private final String errorCode;
    private final int status;

    public PlaceOrderException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }
}
