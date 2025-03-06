package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Product;
import com.example.repository.ProductRepository;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class ProductService extends MainService<Product> {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        // Implementation to be provided
        return null;
    }

    public ArrayList<Product> getProducts() {
        // Implementation to be provided
        return null;
    }

    public Product getProductById(UUID productId) {
        // Implementation to be provided
        return null;
    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        // Implementation to be provided
        return null;
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        // Implementation to be provided
    }

    public void deleteProductById(UUID productId) {
        // Implementation to be provided
    }
}