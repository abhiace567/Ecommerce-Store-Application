package github.abhiace.ecomstore.paymentservice.exception;

import lombok.Getter;

@Getter
public class PaymentInfoNotFoundException extends RuntimeException {

    private final String errorCode;
    public PaymentInfoNotFoundException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
