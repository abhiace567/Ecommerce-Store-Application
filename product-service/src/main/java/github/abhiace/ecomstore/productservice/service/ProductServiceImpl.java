package github.abhiace.ecomstore.productservice.service;

import github.abhiace.ecomstore.productservice.entity.Product;
import github.abhiace.ecomstore.productservice.exception.ProductNotFoundException;
import github.abhiace.ecomstore.productservice.model.ProductRequest;
import github.abhiace.ecomstore.productservice.model.ProductResponse;
import github.abhiace.ecomstore.productservice.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product - {}:{}", productRequest.name(), productRequest.quantity());

        Product product = Product.builder()
                .productName(productRequest.name())
                .price(productRequest.price())
                .quantity(productRequest.quantity())
                .build();

        productRepository.save(product);
        log.info("Product Created");

        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        log.info("Looking for Product in DB");
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Product by Id %d is Not Found", productId), "PRODUCT_NOT_FOUND"));

        ProductResponse productResponse = new ProductResponse(product.getProductId(), product.getProductName(),
                product.getPrice(), product.getQuantity());
        log.info("Product found. {}", productResponse);

        return productResponse;
    }
}
