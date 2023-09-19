package github.abhiace.ecomstore.paymentservice.service;

import github.abhiace.ecomstore.paymentservice.model.PaymentRequest;
import github.abhiace.ecomstore.paymentservice.model.PaymentResponse;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);

    PaymentResponse getPaymentDetailsByOrderId(long orderId);
}
