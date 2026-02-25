package com.melo.inventory.service;

import com.melo.inventory.model.Product;
import com.melo.inventory.model.ProductRequest;
import com.melo.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){this.productRepository = productRepository;}

    public Product createProduct(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        return  productRepository.save(product);
    }
}