package github.abhiace.ecomstore.orderservice.service;

import github.abhiace.ecomstore.orderservice.model.OrderRequest;

public interface OrderService {
    long placeOrder(OrderRequest orderRequest);
}
