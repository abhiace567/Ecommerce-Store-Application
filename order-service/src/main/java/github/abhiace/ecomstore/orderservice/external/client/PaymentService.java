package github.abhiace.ecomstore.orderservice.external.client;

import github.abhiace.ecomstore.orderservice.exception.PlaceOrderException;
import github.abhiace.ecomstore.orderservice.external.request.PaymentRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PAYMENT-SERVICE/payment")
public interface PaymentService {

    @PostMapping
    ResponseEntity<Long> doPayment(@RequestBody PaymentRequest paymentRequest);

    default void fallback(Exception e) {
        throw new PlaceOrderException("payment service is not available", HttpStatus.REQUEST_TIMEOUT.toString(),
                HttpStatus.REQUEST_TIMEOUT.value());
    }
}
