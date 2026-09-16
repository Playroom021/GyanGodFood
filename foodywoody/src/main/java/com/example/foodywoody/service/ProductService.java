package com.example.foodywoody.service;

import com.example.foodywoody.entity.Category;
import com.example.foodywoody.entity.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllAvailable();
    List<Product> findByCategory(Category category);
    List<Product> findAllForAdmin();
    Optional<Product> findById(Long id);
    Product save(Product product);
    void delete(Long id);
}
