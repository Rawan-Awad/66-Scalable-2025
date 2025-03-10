package com.example.service;


import com.example.model.Cart;
import com.example.model.Product;
import com.example.repository.CartRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class CartService extends MainService<Cart> {

    private final CartRepository cartRepository;

    // Constructor for Dependency Injection
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Add a new cart
    public Cart addCart(Cart cart) {
        return cartRepository.addCart(cart);
    }

    // Get all carts
    public ArrayList<Cart> getCarts() {
        return cartRepository.getCarts();
    }

    // Get a specific cart by ID
    public Cart getCartById(UUID cartId) {
        return cartRepository.getCartById(cartId);
    }

    // Get a cart by user ID
    public Cart getCartByUserId(UUID userId) {
        return cartRepository.getCartByUserId(userId);
    }

    public void addProductToCart(UUID cartId, Product product) {
        Cart cart = cartRepository.getCartById(cartId); // ✅ Get cart using cartId

        if (cart == null) {
            throw new IllegalArgumentException("Error: Cart not found!"); // ✅ Controller will catch this
        }

        if (cart.getProducts() == null) {
            cart.setProducts(new ArrayList<>()); // ✅ Ensures list is initialized
        }

        cart.addProduct(product);
        cartRepository.saveAll(cartRepository.getCarts()); // ✅ Save cart update
    }

    public void deleteProductFromCart(UUID cartId, UUID productId) {
        Cart cart = cartRepository.getCartById(cartId); // ✅ Get cart using cartId

        if (cart == null) {
            throw new IllegalArgumentException("Cart is empty"); // ✅ Controller will catch this
        }

        if (cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new IllegalArgumentException("Cart is empty"); // ✅ Controller will handle this
        }

        Product productToRemove = cart.getProducts().stream()
                .filter(p -> p.getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (productToRemove == null) {
            throw new IllegalArgumentException("Error: Product not found in cart!"); // ✅ Controller will handle this
        }

        cart.removeProduct(productToRemove);
        cartRepository.saveAll(cartRepository.getCarts()); // ✅ Save cart update
    }



    // Delete a cart by ID
    public void deleteCartById(UUID cartId) {
        cartRepository.deleteCartById(cartId);
    }
}
