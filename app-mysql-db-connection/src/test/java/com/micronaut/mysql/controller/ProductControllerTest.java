package com.micronaut.mysql.controller;

import com.micronaut.mysql.service.ProductService;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MockBean;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

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
}