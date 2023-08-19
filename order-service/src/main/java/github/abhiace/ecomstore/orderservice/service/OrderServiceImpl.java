package github.abhiace.ecomstore.orderservice.service;

import github.abhiace.ecomstore.orderservice.entity.Order;
import github.abhiace.ecomstore.orderservice.model.OrderRequest;
import github.abhiace.ecomstore.orderservice.model.OrderStatus;
import github.abhiace.ecomstore.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Placing order request - {}", orderRequest);

        Order order = Order.builder()
                .productId(orderRequest.productId())
                .amount(orderRequest.totalAmount())
                .orderDate(Instant.now())
                .quantity(orderRequest.quantity())
                .orderStatus(OrderStatus.CREATED)
                .build();

        orderRepository.save(order);
        log.info("Order placed successfully with order id - {}", order.getOrderId());

        return order.getOrderId();
    }

}