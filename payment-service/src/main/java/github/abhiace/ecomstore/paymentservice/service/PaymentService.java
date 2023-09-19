package github.abhiace.ecomstore.paymentservice.service;

import github.abhiace.ecomstore.paymentservice.model.PaymentRequest;

public interface PaymentService {
    long doPayment(PaymentRequest paymentRequest);
}
