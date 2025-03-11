package com.example.controller;

import com.example.model.Cart;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.repository.CartRepository;
import com.example.repository.ProductRepository;
import com.example.service.CartService;
import com.example.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final ProductService productService;

    // ✅ Constructor-based Dependency Injection
    public CartController(CartService cartService, UserRepository userRepository,
                          ProductRepository productRepository, CartRepository cartRepository, ProductService productService) {
        this.cartService = cartService;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    // ✅ Add a new cart
    @PostMapping("/")
    public Cart addCart(@RequestBody Cart cart) {
        return cartService.addCart(cart);
    }

    // ✅ Get all carts
    @GetMapping("/")
    public ArrayList<Cart> getCarts() {
        return cartService.getCarts();
    }

    // ✅ Get a specific cart by ID
    @GetMapping("/{cartId}")
    public Cart getCartById(@PathVariable UUID cartId) {
        return cartService.getCartById(cartId);
    }

    @PutMapping("/addProduct/{cartId}")
    public String addProductToCart(@PathVariable UUID cartId, @RequestBody Product product) {
        if (product == null) {
            return "Error: Product cannot be null";
        }

        try {
            cartService.addProductToCart(cartId, product);
            return "Product added to cart successfully";
        } catch (RuntimeException e) {
            return "Error: " + e.getMessage();
        }
    }




    // ✅ Delete a cart by ID
    @DeleteMapping("/delete/{cartId}")
    public String deleteCartById(@PathVariable UUID cartId) {
        cartService.deleteCartById(cartId);
        return "Cart deleted successfully";
    }
}
