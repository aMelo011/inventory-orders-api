package com.melo.inventory.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    private String status;
    private LocalDateTime createdAt;

    public Order(){}

    public Long getId(){return id;}
    public AppUser getUser(){return user;}
    public String getStatus(){return status;}
    public LocalDateTime getCreatedAt(){return createdAt;}

    public void setId(Long id){this.id = id;}
    public void setUser(AppUser user){this.user = user;}
    public void setStatus(String status){this.status = status;}
    public void setCreatedAt(LocalDateTime createdAt){this.createdAt = createdAt;}
}
