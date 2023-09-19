package github.abhiace.ecomstore.paymentservice.service;

import github.abhiace.ecomstore.paymentservice.entity.TransactionDetails;
import github.abhiace.ecomstore.paymentservice.model.PaymentRequest;
import github.abhiace.ecomstore.paymentservice.repository.PaymentRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public long doPayment(PaymentRequest paymentRequest) {
        log.info("Recording payment for - {}", paymentRequest);
        TransactionDetails transactionDetails = TransactionDetails.builder()
                .orderId(paymentRequest.orderId())
                .paymentMode(paymentRequest.paymentMode())
                .paymentStatus("SUCCESS")
                .amount(paymentRequest.amount())
                .referenceNumber(paymentRequest.referenceNumber())
                .paymentDate(Instant.now())
                .build();

        paymentRepository.save(transactionDetails);
        log.info("Payment completed with id - {}", transactionDetails.getId());
        return transactionDetails.getId();
    }
}
