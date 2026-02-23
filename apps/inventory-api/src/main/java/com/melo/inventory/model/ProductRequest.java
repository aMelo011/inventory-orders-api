package com.melo.inventory.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public class ProductRequest {
    @NotBlank
    private String name;

    @Positive
    private double price;

    public ProductRequest(String name, double price){
        this.name = name;
        this.price = price;
    }

    public String getName(){return name;}
    public double getPrice(){return price;}

    public void setName(String name){this.name = name;}
    public void setPrice(double price){this.price = price;}
}
