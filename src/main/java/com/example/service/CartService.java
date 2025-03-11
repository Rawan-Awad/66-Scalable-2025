package com.example.service;


import com.example.model.Cart;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.CartRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class CartService extends MainService<Cart> {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // Constructor for Dependency Injection
    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    // Add a new cart
    public Cart addCart(Cart cart) {
        if( cart == null ) {
            throw new IllegalArgumentException("cart is null");
        }
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
        Cart cart = cartRepository.getCartById(cartId);
        ArrayList<User> users = userRepository.getUsers();

        UUID userId = null;

        // Find the user who owns this cart
        for (User user : users) {
            if (cartRepository.getCartByUserId(user.getId()) != null
                    && cartRepository.getCartByUserId(user.getId()).getId().equals(cartId)) {
                userId = user.getId();
                break;
            }
        }

        if (cart == null) {
            if (userId == null) {
                throw new IllegalArgumentException("No user found for this cart ID");
            }
            // Create a new cart for the user
            cart = new Cart(cartId, userId, new ArrayList<>());
            cartRepository.addCart(cart);
        }

        cartRepository.addProductToCart(cart.getId(), product);
    }


    public void deleteProductFromCart(UUID cartId, Product product) {
        Cart cart = cartRepository.getCartById(cartId);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found");
        }
        cartRepository.deleteProductFromCart(cartId, product);
    }



    // Delete a cart by ID
    public void deleteCartById(UUID cartId) {
        cartRepository.deleteCartById(cartId);
    }
}
