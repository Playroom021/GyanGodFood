package com.example.foodywoody.config;

import com.example.foodywoody.entity.*;
import com.example.foodywoody.repository.ProductRepository;
import com.example.foodywoody.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * Seeds a default admin account and a sample menu so the app is usable
 * immediately after `mvn spring-boot:run`, with no manual DB setup.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(ProductRepository productRepository, UserRepository userRepository,
                            PasswordEncoder passwordEncoder) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.findByEmail("admin@foodywoody.com").isEmpty()) {
            User admin = new User();
            admin.setFullName("Admin");
            admin.setEmail("admin@foodywoody.com");
            admin.setPassword(passwordEncoder.encode("Admin@123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }

        if (productRepository.count() == 0) {
            productRepository.save(product("Margherita Pizza", "Classic tomato, mozzarella and fresh basil", "8.99", Category.PIZZA));
            productRepository.save(product("Pepperoni Pizza", "Loaded with spicy pepperoni and mozzarella", "10.49", Category.PIZZA));
            productRepository.save(product("Farmhouse Pizza", "Onion, capsicum, tomato and mushroom", "9.49", Category.PIZZA));
            productRepository.save(product("Classic Cheeseburger", "Beef patty, cheddar, lettuce and house sauce", "6.99", Category.BURGER));
            productRepository.save(product("Veggie Burger", "Grilled veggie patty with fresh greens", "6.49", Category.BURGER));
            productRepository.save(product("Sparkling Lemonade", "Fresh lemon, mint and soda", "2.99", Category.BEVERAGE));
            productRepository.save(product("Cold Coffee", "Chilled coffee blended with milk and ice", "3.49", Category.BEVERAGE));
            productRepository.save(product("Chocolate Brownie", "Warm fudge brownie with a gooey center", "3.99", Category.DESSERT));
            productRepository.save(product("New York Cheesecake", "Creamy cheesecake on a biscuit base", "4.49", Category.DESSERT));
            productRepository.save(product("Garden Salad", "Crisp greens, cherry tomato and vinaigrette", "5.49", Category.SALAD));
        }
    }

    private Product product(String name, String description, String price, Category category) {
        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(new BigDecimal(price));
        p.setCategory(category);
        p.setAvailable(true);
        return p;
    }
}
