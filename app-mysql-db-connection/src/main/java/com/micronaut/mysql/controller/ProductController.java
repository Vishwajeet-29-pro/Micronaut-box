package com.micronaut.mysql.controller;

import com.micronaut.mysql.dto.ProductRequest;
import com.micronaut.mysql.dto.ProductResponse;
import com.micronaut.mysql.service.ProductService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @Operation(summary = "Add a new Product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product Details Added successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductRequest.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided") })
    @Post
    public HttpResponse<ProductResponse> addNewProduct(@Body ProductRequest productRequest) {
        return HttpResponse.created(productService.addProduct(productRequest));
    }

    @Operation(summary = "Get a Product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content) })
    @Get("/{id}")
    public HttpResponse<ProductResponse> findProductById(@PathVariable Long id) {
        return HttpResponse.ok(productService.findProductById(id));
    }

    @Operation(summary = "Retrieve all Products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of all Products retrieved successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)) }),
            @ApiResponse(responseCode = "204", description = "No Product found",
                    content = @Content) })
    @Get
    HttpResponse<List<ProductResponse>> fetchAllProducts() {
        return HttpResponse.ok(productService.findAllProducts());
    }

    @Operation(summary = "Update an existing Product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Details updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input provided",
                    content = @Content) })
    @Put("/{id}")
    HttpResponse<ProductResponse> updateProductById(@PathVariable Long id, @Body ProductRequest productRequest) {
        return HttpResponse.ok(productService.updateProductById(id, productRequest));
    }

    @Operation(summary = "Delete a Product Details by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Product deleted successfully",
                    content = @Content) })
    @Delete("/{id}")
    HttpResponse<Void> deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
        return HttpResponse.noContent();
    }
}
