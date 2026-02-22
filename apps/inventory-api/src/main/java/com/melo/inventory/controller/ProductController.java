package com.melo.inventory.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class ProductController {

    @PostMapping("/products")
    public ProductRequest productRequest(@RequestBody ProductRequest productRequest){

        return productRequest;
    }
}
