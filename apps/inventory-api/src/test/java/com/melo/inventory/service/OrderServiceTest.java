package com.melo.inventory.service;

import com.melo.inventory.model.OrderRequest;
import com.melo.inventory.repository.AppUserRepository;
import com.melo.inventory.repository.OrderItemRepository;
import com.melo.inventory.repository.OrderRepository;
import com.melo.inventory.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderItemRepository orderItemRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void shouldCreateOrderSuccessfully(){

    }

    @Test
    public void shouldThrowWhenEmailNotFound(){
        when(appUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> orderService.createOrder("test@test.com", new OrderRequest()));
    }

    @Test
    public void shouldThrowWhenProductNotFound(){

    }
}
