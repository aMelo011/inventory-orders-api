package com.melo.inventory.service;

import com.melo.inventory.model.*;
import com.melo.inventory.repository.AppUserRepository;
import com.melo.inventory.repository.OrderItemRepository;
import com.melo.inventory.repository.OrderRepository;
import com.melo.inventory.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductRepository productRepository;
    private final AppUserRepository appUserRepository;

    public OrderService(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
                        ProductRepository productRepository, AppUserRepository appUserRepository){
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.productRepository = productRepository;
        this.appUserRepository = appUserRepository;
    }

    public Order createOrder(String email, OrderRequest orderRequest){
        AppUser appUser = appUserRepository.findByEmail(email).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found"));

        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setUser(appUser);
        order.setStatus("PENDING");

        orderRepository.save(order);

        for (OrderItemRequest item : orderRequest.getItems()){
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(item.getQuantity());


            orderItemRepository.save(orderItem);
        }

        return order;
    }
}
