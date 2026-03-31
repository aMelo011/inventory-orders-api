package com.melo.inventory.integration;

import com.melo.inventory.model.*;
import com.melo.inventory.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.web.server.ResponseStatusException;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class ProductIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AppUserRepository appUserRepository;

    @Test
    void shouldCreateProductWithAuth() {
        // register user
        AuthRequest registerRequest = new AuthRequest("test@test.com", "123");

        ResponseEntity<AppUserResponse> registerResponse =
                restTemplate.postForEntity("/api/auth/register", registerRequest, AppUserResponse.class);

        assertEquals(HttpStatus.CREATED, registerResponse.getStatusCode());

        AppUser appUser = appUserRepository.findByEmail("test@test.com").orElseThrow();
        appUser.setRole("ADMIN");
        appUserRepository.save(appUser);

        // login
        ResponseEntity<String> loginResponse =
                restTemplate.postForEntity("/api/auth/login", new AuthRequest("test@test.com", "123"), String.class);
        assertEquals(HttpStatus.OK, loginResponse.getStatusCode());
        String token = loginResponse.getBody();

        // create product with token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ProductRequest productRequest = new ProductRequest("Monitor", new BigDecimal("249.99"));
        HttpEntity<ProductRequest> request = new HttpEntity<>(productRequest, headers);

        ResponseEntity<Product> productResponse =
                restTemplate.postForEntity("/api/products", request, Product.class);

        // verify
        assertEquals(HttpStatus.CREATED, productResponse.getStatusCode());
        assertEquals("Monitor", productResponse.getBody().getName());
    }
}