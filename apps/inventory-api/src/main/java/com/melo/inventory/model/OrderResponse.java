package com.melo.inventory.model;

import java.time.LocalDateTime;
import java.util.List;

public class OrderResponse {
    private Long id;
    private String status;
    private LocalDateTime createdAt;
    private AppUserResponse user;
    private List<OrderItemResponse> items;

    public OrderResponse(Long id, String status, LocalDateTime createdAt, AppUserResponse user, List<OrderItemResponse> items){
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
        this.user = user;
        this.items = items;
    }

    public OrderResponse() {}

    public Long getId() {return id;}
    public String getStatus() {return status;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public AppUserResponse getUser() {return user;}
    public List<OrderItemResponse> getItems() {return items;}

    public void setId(Long id) {this.id = id;}
    public void setStatus(String status) {this.status = status;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
    public void setUser(AppUserResponse user) {this.user = user;}
    public void setItems(List<OrderItemResponse> items) {this.items = items;}
}
