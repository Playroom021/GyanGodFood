package com.example.foodywoody.service;

import com.example.foodywoody.cart.Cart;
import com.example.foodywoody.entity.Order;
import com.example.foodywoody.entity.OrderStatus;
import com.example.foodywoody.entity.User;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order placeOrder(User user, Cart cart, String deliveryAddress, String paymentMethod);
    List<Order> findForUser(User user);
    List<Order> findAll();
    Optional<Order> findById(Long id);
    void updateStatus(Long id, OrderStatus status);
}
