package com.example.foodywoody.service;

import com.example.foodywoody.cart.Cart;
import com.example.foodywoody.cart.CartItem;
import com.example.foodywoody.entity.*;
import com.example.foodywoody.repository.OrderRepository;
import com.example.foodywoody.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Order placeOrder(User user, Cart cart, String deliveryAddress, String paymentMethod) {
        if (cart.isEmpty()) {
            throw new IllegalStateException("Your cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setDeliveryAddress(deliveryAddress);
        order.setPaymentMethod(paymentMethod);
        order.setStatus(OrderStatus.PLACED);

        for (CartItem cartItem : cart.getItems()) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new IllegalStateException("Product no longer available"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setProductName(product.getName());
            item.setUnitPrice(product.getPrice());
            item.setQuantity(cartItem.getQuantity());
            order.getItems().add(item);
        }

        order.setTotalAmount(cart.getTotal());
        Order saved = orderRepository.save(order);
        cart.clear();
        return saved;
    }

    @Override
    public List<Order> findForUser(User user) {
        return orderRepository.findByUserOrderByPlacedAtDesc(user);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAllByOrderByPlacedAtDesc();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public void updateStatus(Long id, OrderStatus status) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
    }
}
