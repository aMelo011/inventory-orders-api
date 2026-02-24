package com.melo.inventory.controller;

import com.melo.inventory.model.ProductRequest;
import com.melo.inventory.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity <ProductRequest> productRequest(@Valid @RequestBody ProductRequest productRequest){

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productRequest));
    }
}
