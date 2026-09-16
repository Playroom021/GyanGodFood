package com.example.foodywoody.controller;

import com.example.foodywoody.cart.Cart;
import com.example.foodywoody.entity.Category;
import com.example.foodywoody.entity.Product;
import com.example.foodywoody.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final Cart cart;

    public ProductController(ProductService productService, Cart cart) {
        this.productService = productService;
        this.cart = cart;
    }

    @GetMapping("/menu")
    public String menu(@RequestParam(required = false) Category category, Model model) {
        List<Product> products = category == null
                ? productService.findAllAvailable()
                : productService.findByCategory(category);

        model.addAttribute("products", products);
        model.addAttribute("categories", Category.values());
        model.addAttribute("selectedCategory", category);
        return "menu";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam Long productId,
                             @RequestParam(defaultValue = "1") int quantity) {
        productService.findById(productId).ifPresent(p -> cart.add(p, Math.max(quantity, 1)));
        return "redirect:/menu";
    }
}
