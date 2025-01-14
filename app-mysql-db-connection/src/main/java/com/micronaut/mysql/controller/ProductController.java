package com.micronaut.mysql.controller;

import com.micronaut.mysql.service.ProductService;
import io.micronaut.http.annotation.Controller;
import lombok.RequiredArgsConstructor;

@Controller("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
}
