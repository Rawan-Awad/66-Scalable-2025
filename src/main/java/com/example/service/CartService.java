package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Cart;
import com.example.models.Product;
import com.example.repository.CartRepository;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class CartService extends MainService<Cart> {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart addCart(Cart cart) {
        // Implementation to be provided
        return null;
    }

    public ArrayList<Cart> getCarts() {
        // Implementation to be provided
        return null;
    }

    public Cart getCartById(UUID cartId) {
        // Implementation to be provided
        return null;
    }

    public Cart getCartByUserId(UUID userId) {
        // Implementation to be provided
        return null;
    }

    public void addProductToCart(UUID cartId, Product product) {
        // Implementation to be provided
    }

    public void deleteProductFromCart(UUID cartId, Product product) {
        // Implementation to be provided
    }

    public void deleteCartById(UUID cartId) {
        // Implementation to be provided
    }
}
