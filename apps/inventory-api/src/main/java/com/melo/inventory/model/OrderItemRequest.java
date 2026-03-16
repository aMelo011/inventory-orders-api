package com.melo.inventory.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class OrderItemRequest {
    @NotNull
    private Long productId;

    @Positive
    private int quantity;

    public OrderItemRequest(Long productId, int quantity){
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getProductId(){return productId;}
    public int getQuantity(){return quantity;}

    public void setProductId(Long productId){this.productId = productId;}
    public void setQuantity(int quantity){this.quantity = quantity;}
}
