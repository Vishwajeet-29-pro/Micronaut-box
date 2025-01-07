package com.micronaut.mysql.dto;

import com.micronaut.mysql.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
    private LocalDateTime addedAt;

    public static ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(), product.getName(),
                product.getDescription(), product.getPrice(),
                product.getStockQuantity(), product.getAddedAt()
        );
    }
}
