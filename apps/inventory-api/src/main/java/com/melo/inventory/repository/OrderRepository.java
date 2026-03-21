package com.melo.inventory.repository;

import com.melo.inventory.model.AppUser;
import com.melo.inventory.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(AppUser user);
}
