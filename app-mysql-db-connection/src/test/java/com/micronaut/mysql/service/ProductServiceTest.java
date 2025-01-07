package com.micronaut.mysql.service;

import com.micronaut.mysql.dto.ProductRequest;
import com.micronaut.mysql.model.Product;
import com.micronaut.mysql.repository.ProductRepository;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@MicronautTest
class ProductServiceTest {

    @Inject
    private ProductService productService;
    @Inject
    private ProductRepository productRepository;

    @MockBean(ProductRepository.class)
    @Replaces(ProductRepository.class)
    ProductRepository mockProductRepository() {
        return mock(ProductRepository.class);
    }

    private Product product;
    private ProductRequest productRequest;

    @BeforeEach
    public void setup() {
        product = new Product(1L, "Mobile", "Oppo Reno 13", 50000.0, 50, LocalDateTime.of(2024, 1, 7, 10, 0));
        productRequest = new ProductRequest("Mobile", "Oppo Reno 13", 50000.0, 50, LocalDateTime.of(2024, 1, 7, 10, 0));
    }
}