package com.example.foodywoody.cart;

import com.example.foodywoody.entity.Product;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A per-user shopping cart kept in the HTTP session.
 * Scoped-proxy so it can be injected straight into singleton controllers/advice.
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart {

    private final Map<Long, CartItem> items = new LinkedHashMap<>();

    public void add(Product product, int quantity) {
        items.merge(product.getId(),
                new CartItem(product.getId(), product.getName(), product.getPrice(), quantity),
                (existing, incoming) -> {
                    existing.setQuantity(existing.getQuantity() + incoming.getQuantity());
                    return existing;
                });
    }

    public void updateQuantity(Long productId, int quantity) {
        if (quantity <= 0) {
            items.remove(productId);
        } else if (items.containsKey(productId)) {
            items.get(productId).setQuantity(quantity);
        }
    }

    public void remove(Long productId) {
        items.remove(productId);
    }

    public void clear() {
        items.clear();
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public int getItemCount() {
        return items.values().stream().mapToInt(CartItem::getQuantity).sum();
    }

    public BigDecimal getTotal() {
        return items.values().stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
