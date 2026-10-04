package com.example.foodywoody.config;

import com.example.foodywoody.cart.Cart;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/** Makes the cart item count available to every page's navbar without repeating code. */
@ControllerAdvice
public class GlobalModelAttributes {

    private final Cart cart;

    public GlobalModelAttributes(Cart cart) {
        this.cart = cart;
    }

    @ModelAttribute("cartItemCount")
    public int cartItemCount() {
        return cart.getItemCount();
    }
}
