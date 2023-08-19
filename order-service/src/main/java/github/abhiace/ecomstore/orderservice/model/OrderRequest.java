package github.abhiace.ecomstore.orderservice.model;

public record OrderRequest(long productId, double totalAmount, long quantity, PaymentMode paymentMode) {
}
