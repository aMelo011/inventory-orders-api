package com.melo.inventory.service;

import com.melo.inventory.model.*;
import com.melo.inventory.repository.AppUserRepository;
import com.melo.inventory.repository.OrderItemRepository;
import com.melo.inventory.repository.OrderRepository;
import com.melo.inventory.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


    public OrderResponse createOrder(String email, OrderRequest orderRequest){
        AppUser appUser = appUserRepository.findByEmail(email).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found"));

        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setUser(appUser);
        order.setStatus(OrderStatus.PENDING);
        orderRepository.save(order);

        List<OrderItemResponse> itemResponses = new ArrayList<>();

        for (OrderItemRequest item : orderRequest.getItems()){
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setOrder(order);
            orderItem.setQuantity(item.getQuantity());
            orderItemRepository.save(orderItem);

            OrderItemResponse orderItemResponse = new OrderItemResponse();
            orderItemResponse.setProductId(product.getId());
            orderItemResponse.setQuantity(orderItem.getQuantity());
            orderItemResponse.setProductName(orderItem.getProduct().getName());

            itemResponses.add(orderItemResponse);
        }

        AppUserResponse appUserResponse = new AppUserResponse();
        appUserResponse.setEmail(appUser.getEmail());
        appUserResponse.setId(appUser.getId());

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setStatus(order.getStatus().name());
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUser(appUserResponse);
        orderResponse.setItems(itemResponses);

        return orderResponse;
    }

    public Page<OrderResponse> getOrdersByUser(String email, Pageable pageable){
        AppUser appUser = appUserRepository.findByEmail(email).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email not found"));

        Page<Order> orders = orderRepository.findByUser(appUser, pageable);


        return orders.map(order -> {
                List<OrderItemResponse> itemResponses = new ArrayList<>();
                List<OrderItem> orderItems = orderItemRepository.findByOrder(order);

                for (OrderItem orderItem : orderItems) {

                    OrderItemResponse orderItemResponse = new OrderItemResponse();
                    orderItemResponse.setProductId(orderItem.getProduct().getId());
                    orderItemResponse.setQuantity(orderItem.getQuantity());
                    orderItemResponse.setProductName(orderItem.getProduct().getName());

                    itemResponses.add(orderItemResponse);
                }

                AppUserResponse appUserResponse = new AppUserResponse();
                appUserResponse.setId(appUser.getId());
                appUserResponse.setEmail(appUser.getEmail());

                OrderResponse orderResponse = new OrderResponse();
                orderResponse.setId(order.getId());
                orderResponse.setStatus(order.getStatus().name());
                orderResponse.setCreatedAt(order.getCreatedAt());
                orderResponse.setUser(appUserResponse);
                orderResponse.setItems(itemResponses);
            return orderResponse;
        });
    }
}
