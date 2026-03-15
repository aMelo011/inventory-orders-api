package com.melo.inventory.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    public OrderItem(){}

    public Long getId(){return id;}
    public Order getOrder(){return order;}
    public Product getProduct(){return product;}
    public int getQuantity(){return quantity;}

    public void setId(Long id){this.id = id;}
    public void setProduct(Product product){this.product = product;}
    public void setOrder(Order order){this.order = order;}
    public void setQuantity(int quantity){this.quantity = quantity;}
}
