package com.micronaut.mysql.controller;

import com.micronaut.mysql.dto.ProductRequest;
import com.micronaut.mysql.dto.ProductResponse;
import com.micronaut.mysql.service.ProductService;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MicronautTest
class ProductControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Inject
    private ProductService productService;

    @MockBean(ProductService.class)
    ProductService mockProductService() {
        return mock(ProductService.class);
    }

    @Test
    void test_create_product_should_return_product_response_with_201_created() {
        ProductRequest productRequest = new ProductRequest("Mobile", "Oppo Reno 14", 50000.0, 50, LocalDateTime.of(2025, 1, 14, 8, 0));
        ProductResponse productResponse = new ProductResponse(1L, "Mobile", "Oppo Reno 14", 50000.0, 50, LocalDateTime.of(2025, 1, 14, 8, 0));

        when(productService.addProduct(productRequest)).thenReturn(productResponse);

        HttpResponse<ProductResponse> productResponseHttpResponse = httpClient.toBlocking().exchange(
                HttpRequest.POST("/api/v1/products", productRequest),
                ProductResponse.class
        );

        assertEquals(201, productResponseHttpResponse.getStatus().getCode());
        assertNotNull(productResponseHttpResponse);
        assertEquals("Mobile", productResponseHttpResponse.body().getName());
        assertEquals(50000.0, productResponseHttpResponse.body().getPrice());
        assertEquals(productRequest.getAddedAt(), productResponseHttpResponse.body().getAddedAt());
    }

    @Test
    void test_get_product_by_id_should_return_product_response_with_id_and_200_ok() {
        ProductResponse productResponse = new ProductResponse(1L, "Mobile", "Oppo Reno 14", 50000.0, 50, LocalDateTime.of(2025, 1, 14, 8, 0));

        when(productService.findProductById(1L)).thenReturn(productResponse);

        HttpResponse<ProductResponse> productResponseHttpResponse = httpClient.toBlocking().exchange(
                HttpRequest.GET("/api/v1/products/" + 1L),
                ProductResponse.class
        );

        assertNotNull(productResponseHttpResponse);
        assertEquals(200, productResponseHttpResponse.getStatus().getCode());
        assertEquals("Mobile", productResponseHttpResponse.body().getName());
        assertEquals(50000.0, productResponseHttpResponse.body().getPrice());
        assertEquals(productResponse.getAddedAt(), productResponseHttpResponse.body().getAddedAt());
    }

    @Test
    void test_fetch_all_products_should_return_list_of_product_response_and_200_ok() {
        List<ProductResponse> productResponses = List.of(
                new ProductResponse(1L, "Mobile", "Oppo Reno 14", 50000.0, 50, LocalDateTime.of(2025, 1, 14, 8, 0)),
                new ProductResponse(2L, "Laptop", "Dell something", 65000.0, 40, LocalDateTime.of(2025, 1, 14, 8, 0))
        );

        when(productService.findAllProducts()).thenReturn(productResponses);

        HttpResponse<List<ProductResponse>> listHttpResponse = httpClient.toBlocking().exchange(
                HttpRequest.GET("/api/v1/products"),
                Argument.listOf(ProductResponse.class)
        );

        assertNotNull(listHttpResponse);
        assertEquals(2, listHttpResponse.body().size());
        assertEquals("Mobile", listHttpResponse.body().getFirst().getName());
        assertEquals(65000.0, listHttpResponse.body().getLast().getPrice());
        assertEquals(50, listHttpResponse.body().getFirst().getStockQuantity());
    }

    @Test
    void test_update_product_by_id_should_return_product_response_and_200_ok() {
        ProductRequest productRequest = new ProductRequest("Mobile Phone", "Oppo Reno 15", 55000.0, 40, LocalDateTime.of(2025, 1, 14, 8, 0));
        Long id = 1L;
        ProductResponse productResponse = new ProductResponse(1L, "Mobile Phone", "Oppo Reno 15", 55000.0, 40, LocalDateTime.of(2025, 1, 14, 8, 0));

        when(productService.updateProductById(id, productRequest)).thenReturn(productResponse);

        HttpResponse<ProductResponse> productResponseHttpResponse = httpClient.toBlocking().exchange(
                HttpRequest.PUT("/api/v1/products/"+ id, productRequest),
                ProductResponse.class
        );

        assertNotNull(productResponseHttpResponse);
        assertEquals(productRequest.getName(), productResponseHttpResponse.body().getName());
        assertEquals(productResponse.getDescription(), productResponseHttpResponse.body().getDescription());
        assertEquals(productRequest.getPrice(), productResponseHttpResponse.body().getPrice());
        assertEquals(productResponse.getStockQuantity(), productResponseHttpResponse.body().getStockQuantity());
    }

    @Test
    void test_delete_product_by_id_should_delete_product_and_return_204_no_content() {
        Long id = 1L;

        doNothing().when(productService).deleteProductById(id);

        HttpResponse<Void> response = httpClient.toBlocking().exchange(
                HttpRequest.DELETE("/api/v1/products/"+id), Void.class
        );

        assertEquals(204, response.getStatus().getCode());
        verify(productService, times(1)).deleteProductById(id);
    }
}