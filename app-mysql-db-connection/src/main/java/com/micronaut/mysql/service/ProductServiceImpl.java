package com.micronaut.mysql.service;

import com.micronaut.mysql.dto.ProductRequest;
import com.micronaut.mysql.dto.ProductResponse;
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
        return null;
    }

    @Override
    public ProductResponse findProductById(Long id) {
        return null;
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
