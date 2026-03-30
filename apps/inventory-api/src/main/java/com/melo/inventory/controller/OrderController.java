package com.melo.inventory.controller;

import com.melo.inventory.model.OrderRequest;
import com.melo.inventory.model.OrderResponse;
import com.melo.inventory.model.Product;
import com.melo.inventory.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService){this.orderService = orderService;}

    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest, Authentication authentication){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(authentication.getName(), orderRequest));
    }

    @GetMapping("/orders")
    public ResponseEntity<Page<OrderResponse>> getOrder(Authentication authentication, Pageable pageable){
        return ResponseEntity.ok(orderService.getOrdersByUser(authentication.getName(), pageable));
    }
}
