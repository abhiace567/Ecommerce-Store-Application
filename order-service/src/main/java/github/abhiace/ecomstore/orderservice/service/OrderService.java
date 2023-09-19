package github.abhiace.ecomstore.orderservice.service;

import github.abhiace.ecomstore.orderservice.model.OrderRequest;
import github.abhiace.ecomstore.orderservice.model.OrderResponse;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);

    OrderResponse getOrderDetails(long orderId);
}
