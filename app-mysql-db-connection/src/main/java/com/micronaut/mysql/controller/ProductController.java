package com.micronaut.mysql.controller;

import com.micronaut.mysql.dto.ProductRequest;
import com.micronaut.mysql.dto.ProductResponse;
import com.micronaut.mysql.service.ProductService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Post
    public HttpResponse<ProductResponse> addNewProduct(@Body ProductRequest productRequest) {
        return HttpResponse.created(productService.addProduct(productRequest));
    }

    @Get("/{id}")
    public HttpResponse<ProductResponse> findProductById(@PathVariable Long id) {
        return HttpResponse.ok(productService.findProductById(id));
    }

    @Get
    HttpResponse<List<ProductResponse>> fetchAllProducts() {
        return HttpResponse.ok(productService.findAllProducts());
    }

    @Put("/{id}")
    HttpResponse<ProductResponse> updateProductById(@PathVariable Long id, @Body ProductRequest productRequest) {
        return HttpResponse.ok(productService.updateProductById(id, productRequest));
    }
}
