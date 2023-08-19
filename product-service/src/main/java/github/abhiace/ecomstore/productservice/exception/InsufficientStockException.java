package github.abhiace.ecomstore.productservice.exception;

import lombok.Getter;

@Getter
public class InsufficientStockException extends RuntimeException {

    private final String errorCode;

    public InsufficientStockException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
