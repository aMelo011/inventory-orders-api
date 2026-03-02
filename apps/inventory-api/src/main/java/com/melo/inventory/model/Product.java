package com.melo.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;

    public Product() {}

    public Long getId(){return id;}
    public String getName(){return name;}
    public BigDecimal getPrice(){return price;}

    public void setId(Long id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setPrice(BigDecimal price){this.price = price;}
}
