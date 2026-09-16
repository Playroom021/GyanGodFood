package com.example.foodywoody.repository;

import com.example.foodywoody.entity.Category;
import com.example.foodywoody.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByAvailableTrue();
    List<Product> findByCategoryAndAvailableTrue(Category category);
}
