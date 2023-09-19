package github.abhiace.ecomstore.orderservice.external.request;

import github.abhiace.ecomstore.orderservice.model.PaymentMode;

public record PaymentRequest(long orderId, double amount, String referenceNumber, PaymentMode paymentMode) {
}
