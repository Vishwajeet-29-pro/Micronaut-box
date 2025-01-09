package com.micronaut.mysql.service;

import com.micronaut.mysql.dto.ProductRequest;
import com.micronaut.mysql.dto.ProductResponse;
import com.micronaut.mysql.model.Product;
import com.micronaut.mysql.repository.ProductRepository;
import jakarta.inject.Singleton;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Singleton
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        Product product = productRepository.save(ProductRequest.toProduct(productRequest));
        return ProductResponse.toProductResponse(product);
    }

    @Override
    public ProductResponse findProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow();
        return ProductResponse.toProductResponse(product);
    }

    @Override
    public List<ProductResponse> findAllProducts() {
        return List.of();
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest productRequest) {
        return null;
    }

    @Override
    public void deleteProductById(Long id) {

    }
}
