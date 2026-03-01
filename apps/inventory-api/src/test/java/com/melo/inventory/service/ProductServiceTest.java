package com.melo.inventory.service;

import com.melo.inventory.model.Product;
import com.melo.inventory.model.ProductRequest;
import com.melo.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void shouldCreateProductSuccessfully(){
        Product product = new Product();
        product.setId(1L);
        product.setName("test01");
        product.setPrice(33.33);

        ProductRequest productRequest = new ProductRequest("test01", 33.33);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.createProduct(productRequest);
        assertEquals(33.33, result.getPrice());
    }

    @Test
    public void shouldReturnAllProducts(){
        Product product1 = new Product();
        Product product2 = new Product();

        product1.setName("Monitor");
        product1.setPrice(249.99);

        product2.setName("Keyboard");
        product2.setPrice(39.99);

        when(productRepository.findAll()).thenReturn(List.of(product1, product2));

        assertEquals(2, productService.getAllProducts().size());
    }

    @Test
    public void shouldThrownWhenProductNotFound(){
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> productService.getById(1L));
    }
}
