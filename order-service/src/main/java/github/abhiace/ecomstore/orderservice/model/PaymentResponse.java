package github.abhiace.ecomstore.orderservice.model;

import java.time.Instant;

public record PaymentResponse(long paymentId, String paymentStatus, PaymentMode paymentMode, double amount,
                              Instant paymentDate, long orderId) {
}
