package com.example.foodywoody.controller;

import com.example.foodywoody.cart.Cart;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final Cart cart;

    public CartController(Cart cart) {
        this.cart = cart;
    }

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("items", cart.getItems());
        model.addAttribute("total", cart.getTotal());
        return "cart";
    }

    @PostMapping("/update")
    public String updateQuantity(@RequestParam Long productId, @RequestParam int quantity) {
        cart.updateQuantity(productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String remove(@PathVariable Long productId) {
        cart.remove(productId);
        return "redirect:/cart";
    }
}
