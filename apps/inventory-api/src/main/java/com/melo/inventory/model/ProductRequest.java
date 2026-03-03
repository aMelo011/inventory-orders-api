package com.melo.inventory.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;


public class ProductRequest {
    @NotBlank
    private String name;

    @Positive
    private BigDecimal price;

    private Long categoryId;

    public ProductRequest(String name, BigDecimal price){
        this.name = name;
        this.price = price;
    }

    public String getName(){return name;}
    public BigDecimal getPrice(){return price;}
    public Long getCategoryId(){return categoryId;}

    public void setName(String name){this.name = name;}
    public void setPrice(BigDecimal price){this.price = price;}
    public void setCategoryId(Long categoryId){this.categoryId = categoryId;}
}
