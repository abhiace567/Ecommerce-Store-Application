package github.abhiace.ecomstore.paymentservice.model;

public record PaymentRequest(long orderId, double amount, String referenceNumber, PaymentMode paymentMode) {
}
