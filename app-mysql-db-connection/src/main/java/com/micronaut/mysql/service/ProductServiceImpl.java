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
        List<Product> productResponses = productRepository.findAll();
        return productResponses.stream().map(
                ProductResponse::toProductResponse
        ).toList();
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setName(productRequest.getName());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setStockQuantity(productRequest.getStockQuantity());
        product.setAddedAt(productRequest.getAddedAt());

        Product updatedProduct = productRepository.save(product);
        return ProductResponse.toProductResponse(updatedProduct);
    }

    @Override
    public void deleteProductById(Long id) {
        if(!productRepository.existsById(id)) {
            throw new RuntimeException("Product with id: "+id+" not found");
        }
        productRepository.deleteById(id);
    }
}
