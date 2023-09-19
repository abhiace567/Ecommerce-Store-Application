package github.abhiace.ecomstore.orderservice.service;

import github.abhiace.ecomstore.orderservice.entity.Order;
import github.abhiace.ecomstore.orderservice.exception.OrderNotFoundException;
import github.abhiace.ecomstore.orderservice.exception.RestTemplateErrorHandler;
import github.abhiace.ecomstore.orderservice.external.client.PaymentService;
import github.abhiace.ecomstore.orderservice.external.client.ProductService;
import github.abhiace.ecomstore.orderservice.external.request.PaymentRequest;
import github.abhiace.ecomstore.orderservice.model.*;
import github.abhiace.ecomstore.orderservice.repository.OrderRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@Service
@Log4j2
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final PaymentService paymentService;
    private final RestTemplate restTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService,
                            PaymentService paymentService, RestTemplate restTemplate) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.paymentService = paymentService;
        this.restTemplate = restTemplate;
        this.restTemplate.setErrorHandler(new RestTemplateErrorHandler());
    }

    @Override
    public long placeOrder(OrderRequest orderRequest) {
        log.info("Placing order request - {}", orderRequest);

        productService.reduceQuantity(orderRequest.productId(),
                orderRequest.quantity());

        Order order = Order.builder()
                .productId(orderRequest.productId())
                .amount(orderRequest.totalAmount())
                .orderDate(Instant.now())
                .quantity(orderRequest.quantity())
                .orderStatus(OrderStatus.CREATED)
                .build();

        orderRepository.save(order);

        log.info("calling payment service to complete the payment");
        PaymentRequest paymentRequest = new PaymentRequest(order.getOrderId(), order.getAmount(),
                "" + (long) Math.random() * 100000, orderRequest.paymentMode());

        try {
            paymentService.doPayment(paymentRequest);
            log.info("Payment completed");
            order.setOrderStatus(OrderStatus.PAYMENT_SUCCESSFUL);
            log.info("updated order status");
        } catch (Exception e) {
            log.info("Error occurred while processing payment");
            order.setOrderStatus(OrderStatus.FAILED);
        }

        orderRepository.save(order);

        log.info("Order placed successfully with order id - {}", order.getOrderId());

        return order.getOrderId();
    }

    @Override
    public OrderResponse getOrderDetails(long orderId) {
        log.info("find order details for order id: {}", orderId);
        Order orderDetails = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("no order found with id: " + orderId,
                        HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value()));

        log.info("fetching product info for product id: {}", orderDetails.getProductId());
        ProductResponse productDetails =
                restTemplate.getForObject("http://PRODUCT-SERVICE/product/" + orderDetails.getProductId(),
                        ProductResponse.class);

        log.info("fetching payment info for order id: {}", orderId);
        PaymentResponse paymentDetails =
                restTemplate.getForObject("http://PAYMENT-SERVICE/payment/order/" + orderDetails.getOrderId(),
                        PaymentResponse.class);

        return new OrderResponse(orderDetails.getOrderId(), orderDetails.getOrderDate(),
                orderDetails.getOrderStatus(), orderDetails.getAmount(), productDetails, paymentDetails);
    }

}
