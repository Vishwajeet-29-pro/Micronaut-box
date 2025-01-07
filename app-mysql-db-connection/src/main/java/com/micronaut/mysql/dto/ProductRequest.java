package com.micronaut.mysql.dto;

import com.micronaut.mysql.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private LocalDateTime addedAt;

    public static Product toProduct(ProductRequest productRequest) {
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setAddedAt(productRequest.getAddedAt());

        return product;
    }
}
