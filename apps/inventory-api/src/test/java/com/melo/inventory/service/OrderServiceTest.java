package com.melo.inventory.service;

import com.melo.inventory.model.*;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        appUser.setEmail("test@test.com");

        Product product = new Product();
        product.setId(1L);
        product.setName("test product");

        Order order = new Order();
        order.setId(1L);
        order.setStatus(OrderStatus.PENDING);

        OrderItemRequest orderItemRequest = new OrderItemRequest(1L, 3);
        OrderRequest orderRequest = new OrderRequest();

        List<OrderItemRequest> orderItemRequestList = new ArrayList<>();

        orderItemRequestList.add(orderItemRequest);
        orderRequest.setItems(orderItemRequestList);

        when(appUserRepository.findByEmail("test@test.com")).thenReturn(Optional.of(appUser));
        when(orderRepository.save(any(Order.class))).thenReturn(order);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderItemRepository.save(any(OrderItem.class))).thenReturn(new OrderItem());

        OrderResponse result = orderService.createOrder("test@test.com", orderRequest);

        assertEquals("PENDING", result.getStatus());
        assertEquals("test@test.com", result.getUser().getEmail());
        assertEquals(1,result.getItems().size());
    }

    @Test
    public void shouldThrowWhenEmailNotFound(){
        when(appUserRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> orderService.createOrder("test@test.com", new OrderRequest()));
    }

    @Test
    public void shouldThrowWhenProductNotFound(){
        AppUser appUser = new AppUser();
        appUser.setId(1L);
        appUser.setEmail("test@test.com");

        OrderItemRequest orderItemRequest = new OrderItemRequest(1L, 2);

        List<OrderItemRequest> orderItemRequestList = new ArrayList<>();
        orderItemRequestList.add(orderItemRequest);

        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setItems(orderItemRequestList);

        when(appUserRepository.findByEmail("test@test.com")).thenReturn(Optional.of(appUser));
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());
        when(productRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> orderService.createOrder("test@test.com",orderRequest));
    }
}
