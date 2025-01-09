package com.micronaut.mysql.service;

import com.micronaut.mysql.dto.ProductRequest;
import com.micronaut.mysql.dto.ProductResponse;
import com.micronaut.mysql.model.Product;
import com.micronaut.mysql.repository.ProductRepository;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    @Test
    void add_new_product_should_return_product_response() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductResponse productResponse = productService.addProduct(productRequest);

        assertNotNull(productResponse);
        assertEquals("Mobile", productResponse.getName());
        assertEquals(50000.0, productResponse.getPrice());
        assertEquals(50, productResponse.getStockQuantity());
        assertEquals(LocalDateTime.of(2024, 1, 7, 10, 0), productResponse.getAddedAt());
    }

    @Test
    void find_by_id_should_return_product_response() {
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        ProductResponse productResponse = productService.findProductById(1L);

        assertNotNull(productResponse);
        assertEquals("Mobile", productResponse.getName());
        assertEquals(50000.0, productResponse.getPrice());
        assertEquals(50, productResponse.getStockQuantity());
        assertEquals(LocalDateTime.of(2024, 1, 7, 10, 0), productResponse.getAddedAt());
    }

    @Test
    void find_all_product_should_return_list_of_product_response() {
        when(productRepository.findAll()).thenReturn(List.of(product));

        List<ProductResponse> productResponses = productService.findAllProducts();

        assertNotNull(productResponses);
        assertEquals(1, productResponses.size());
        assertEquals("Mobile", productResponses.getFirst().getName());
        assertEquals(50000.0, productResponses.getFirst().getPrice());
        assertEquals(50, productResponses.getFirst().getStockQuantity());
    }

}