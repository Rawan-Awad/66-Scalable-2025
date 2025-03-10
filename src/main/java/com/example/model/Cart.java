package com.example.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class Cart {
    private UUID id;
    private UUID userId;
    private List<Product> products = new ArrayList<>();

    // Constructors
    public Cart() {} // Default constructor

    public Cart(UUID userId) { // Constructor for creating a cart for a user
        this.id = UUID.randomUUID();
        this.userId = userId;
    }

    public Cart(UUID id, UUID userId, List<Product> products) { // Constructor for fully initializing a cart
        this.id = id;
        this.userId = userId;
        this.products = products;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}
