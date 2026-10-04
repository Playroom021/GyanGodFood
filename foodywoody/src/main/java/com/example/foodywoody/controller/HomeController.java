package com.example.foodywoody.controller;

import com.example.foodywoody.entity.Product;
import com.example.foodywoody.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> all = productService.findAllAvailable();
        model.addAttribute("featured", all.stream().limit(4).toList());
        return "index";
    }
}
