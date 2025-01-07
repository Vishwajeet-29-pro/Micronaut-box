package com.micronaut.mysql.service;

import com.micronaut.mysql.dto.ProductRequest;
import com.micronaut.mysql.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);
    ProductResponse findProductById(Long id);
    List<ProductResponse> findAllProducts();
    ProductResponse updateProductById(Long id, ProductRequest productRequest);
    void deleteProductById(Long id);
}
