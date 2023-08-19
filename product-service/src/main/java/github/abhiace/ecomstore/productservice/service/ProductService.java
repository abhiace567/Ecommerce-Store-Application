package github.abhiace.ecomstore.productservice.service;

import github.abhiace.ecomstore.productservice.model.ProductRequest;
import github.abhiace.ecomstore.productservice.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(long productId);
}
