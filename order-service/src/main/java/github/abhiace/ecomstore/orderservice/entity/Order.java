package github.abhiace.ecomstore.orderservice.entity;

import github.abhiace.ecomstore.orderservice.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "ORDER_DETAILS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long orderId;
    private long productId;
    private long quantity;
    private Instant orderDate;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @Column(name = "total_amount")
    private double amount;
}
