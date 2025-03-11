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
        if(productRepository.getProductById(product.getId())!=null) {
            throw new IllegalArgumentException("Product already exists");
        }
        return productRepository.addProduct(product);

    }

    public ArrayList<Product> getProducts() {
        ArrayList<Product> products = productRepository.getProducts();
        if (products.isEmpty()) {
            throw new IllegalArgumentException("No users found");
        }
        return products;

    }

    public Product getProductById(UUID productId) {
        Product product =productRepository.getProductById(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        return  product;

    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        Product product = productRepository.updateProduct( productId, newName, newPrice);
        if(product == null) {
            throw new IllegalArgumentException("Product not found");
        }

        return product;
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        productRepository.applyDiscount(discount, productIds);
    }

    public void deleteProductById(UUID productId) {
        if(productId == null) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteProductById(productId);
    }
}