package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Product;
import com.example.repository.ProductRepository;
import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class ProductService extends MainService<Product> {
    private final ProductRepository productRepository;
    private final Product product;

    @Autowired
    public ProductService(ProductRepository productRepository, Product product) {
        this.productRepository = productRepository;
        this.product = product;
    }

    public Product addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        return productRepository.addProduct(product);

    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = productRepository.getProducts();
        if (products.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return products;

    }

    public Product getProductById(UUID productId) {
        return productRepository.getProductById(productId);

    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        return productRepository.updateProduct( productId, newName, newPrice);
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        productRepository.applyDiscount(discount, productIds);
    }

    public void deleteProductById(UUID productId) {
        productRepository.deleteProductById(productId);
    }
}