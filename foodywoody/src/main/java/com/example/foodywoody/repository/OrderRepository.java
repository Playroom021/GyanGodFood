package com.example.foodywoody.repository;

import com.example.foodywoody.entity.Order;
import com.example.foodywoody.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(User user);
    List<Order> findAllByOrderByPlacedAtDesc();
}
