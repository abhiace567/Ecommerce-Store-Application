package github.abhiace.ecomstore.orderservice.model;

import java.time.Instant;

public record OrderResponse(long orderId, Instant orderDate, OrderStatus orderStatus, double amount,
                            ProductResponse productDetails, PaymentResponse paymentDetails) {
}
