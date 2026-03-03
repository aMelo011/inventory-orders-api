package com.melo.inventory.service;

import com.melo.inventory.model.Product;
import com.melo.inventory.model.ProductRequest;
import com.melo.inventory.repository.CategoryRepository;
import com.melo.inventory.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product createProduct(ProductRequest productRequest){
        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        return  productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getById(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public Product putProduct(Long id, ProductRequest productRequest){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        return productRepository.save(product);
    }

    public void deleteProduct(Long id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

       productRepository.delete(product);
    }


}