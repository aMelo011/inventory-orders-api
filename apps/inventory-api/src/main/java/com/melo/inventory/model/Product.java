package com.melo.inventory.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {}

    public Long getId(){return id;}
    public String getName(){return name;}
    public BigDecimal getPrice(){return price;}
    public Category getCategory(){return  category;}

    public void setId(Long id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setPrice(BigDecimal price){this.price = price;}
    public void setCategory(Category category){this.category = category;}
}
