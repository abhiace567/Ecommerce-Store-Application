package github.abhiace.ecomstore.orderservice.external.client;

import github.abhiace.ecomstore.orderservice.exception.PlaceOrderException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CircuitBreaker(name = "external", fallbackMethod = "fallback")
@FeignClient(name = "PRODUCT-SERVICE/product")
public interface ProductService {

    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId,
                                               @RequestParam("quantity") long reduceBy);

    default void fallback(Exception e) {
        throw new PlaceOrderException("product service is not available", HttpStatus.REQUEST_TIMEOUT.toString(),
                HttpStatus.REQUEST_TIMEOUT.value());
    }
}
