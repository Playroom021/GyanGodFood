package com.example.foodywoody.service;

import com.example.foodywoody.entity.Category;
import com.example.foodywoody.entity.Product;
import com.example.foodywoody.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAllAvailable() {
        return productRepository.findByAvailableTrue();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategoryAndAvailableTrue(category);
    }

    @Override
    public List<Product> findAllForAdmin() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
