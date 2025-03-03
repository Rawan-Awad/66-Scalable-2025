package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class ProductRepository extends MainRepository<Product> {
    public static List<Product> products = new ArrayList<>();

    public ProductRepository() {
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

    @Override
    protected String getDataPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataPath'");
    }

    @Override
    protected Class<Product[]> getArrayType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArrayType'");
    }
}
