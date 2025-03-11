package com.example.model;

import com.example.model.Product;
import org.springframework.stereotype.Component;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {

    private UUID id;
    private UUID userId;
    private List<Product> products  = new ArrayList<>();

    // Default Constructor
    public Cart() {
//        this.id = UUID.randomUUID();
//        this.products = new ArrayList<>();
    }

    public Cart(UUID userId, List<Product> products) {
//        this.id = UUID.randomUUID();
        this.userId = userId;
        this.products = new ArrayList<>();
    }

    // Constructor with all attributes
    public Cart(UUID id, UUID userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.products = new ArrayList<>();
//        this.products = products != null ? products : new ArrayList<>();
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = (products != null) ? products : new ArrayList<>(); // ✅ Avoids null pointer
    }

    // Utility method to add a product to the cart
    public void addProduct(Product product) {
        if (product != null) {
            this.products.add(product);
        }
    }

    // Utility method to remove a product from the cart
    public void removeProduct(Product product) {
        this.products.remove(product);
    }
}
