package github.abhiace.ecomstore.paymentservice.entity;

import github.abhiace.ecomstore.paymentservice.model.PaymentMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "payment_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long orderId;
    private String referenceNumber;
    @Enumerated(EnumType.STRING)
    @Column(name = "mode")
    private PaymentMode paymentMode;
    private Instant paymentDate;
    @Column(name = "status")
    private String paymentStatus;
    private double amount;
}
