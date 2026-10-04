package com.example.foodywoody.controller;

import com.example.foodywoody.cart.Cart;
import com.example.foodywoody.entity.Order;
import com.example.foodywoody.entity.User;
import com.example.foodywoody.service.OrderService;
import com.example.foodywoody.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final Cart cart;

    public OrderController(OrderService orderService, UserService userService, Cart cart) {
        this.orderService = orderService;
        this.userService = userService;
        this.cart = cart;
    }

    @GetMapping("/checkout")
    public String checkoutForm(Model model) {
        if (cart.isEmpty()) {
            return "redirect:/cart";
        }
        model.addAttribute("items", cart.getItems());
        model.addAttribute("total", cart.getTotal());
        return "checkout";
    }

    @PostMapping("/checkout")
    public String placeOrder(@RequestParam String deliveryAddress,
                              @RequestParam String paymentMethod,
                              @AuthenticationPrincipal UserDetails principal,
                              Model model) {
        User user = userService.findByEmail(principal.getUsername())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        try {
            Order order = orderService.placeOrder(user, cart, deliveryAddress, paymentMethod);
            return "redirect:/orders/" + order.getId();
        } catch (IllegalStateException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            model.addAttribute("items", cart.getItems());
            model.addAttribute("total", cart.getTotal());
            return "checkout";
        }
    }

    @GetMapping("/orders")
    public String myOrders(@AuthenticationPrincipal UserDetails principal, Model model) {
        User user = userService.findByEmail(principal.getUsername())
                .orElseThrow(() -> new IllegalStateException("User not found"));
        model.addAttribute("orders", orderService.findForUser(user));
        return "orders";
    }

    @GetMapping("/orders/{id}")
    public String orderDetail(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        model.addAttribute("order", order);
        return "order-confirmation";
    }
}
