package com.melo.inventory.model;

public class OrderItemResponse {
    private Long productId;
    private String productName;
    private int quantity;

    public OrderItemResponse(Long productId, String productName, int quantity){
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
    }

    public OrderItemResponse() {

    }

    public Long getProductId() {return productId;}
    public String getProductName() {return productName;}
    public int getQuantity() {return quantity;}

    public void setProductId(Long productId) {this.productId = productId;}
    public void setProductName(String productName) {this.productName = productName;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
}
