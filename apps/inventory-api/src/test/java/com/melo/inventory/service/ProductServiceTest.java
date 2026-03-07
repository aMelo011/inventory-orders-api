package com.melo.inventory.service;

import com.melo.inventory.model.Category;
import com.melo.inventory.model.Product;
import com.melo.inventory.model.ProductRequest;
import com.melo.inventory.repository.CategoryRepository;
import com.melo.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
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
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void shouldCreateProductSuccessfully(){
        Category category = new Category();
        category.setId(1L);
        category.setName("Electronics");

        Product product = new Product();
        product.setId(1L);
        product.setName("test01");
        product.setPrice(BigDecimal.valueOf(33.33));
        product.setCategory(category);

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));

        ProductRequest productRequest = new ProductRequest("test01", BigDecimal.valueOf(33.33));
        productRequest.setCategoryId(1L);

        when(productRepository.save(any(Product.class))).thenReturn(product);


        Product result = productService.createProduct(productRequest);
        assertEquals(BigDecimal.valueOf(33.33), result.getPrice());
        assertEquals(1L, result.getCategory().getId());
    }

    @Test
    public void shouldReturnAllProducts(){
        Pageable pageable = PageRequest.of(0, 10);
        Product product1 = new Product();
        Product product2 = new Product();

        product1.setName("Monitor");
        product1.setPrice(BigDecimal.valueOf((249.99)));

        product2.setName("Keyboard");
        product2.setPrice(BigDecimal.valueOf((39.99)));

        Page<Product> page = new PageImpl<>(List.of(product1, product2));

        when(productRepository.findAll(pageable)).thenReturn(page);

        assertEquals(2, productService.getAllProducts(pageable).getTotalElements());
    }

    @Test
    public void shouldThrownWhenProductNotFound(){
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> productService.getById(1L));
    }
}
