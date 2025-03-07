package com.example.repository;

import org.springframework.stereotype.Repository;
import com.example.models.Product;
import java.util.ArrayList;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class ProductRepository extends MainRepository<Product> {
    public static final String FILE_PATH = "src/main/java/com/example/data/products.json";

    public ProductRepository() {
    }

    @Override
    protected String getDataPath() {
        return FILE_PATH;
    }

    @Override
    protected Class<Product[]> getArrayType() {
        return Product[].class;
    }

    // Get all users
    public ArrayList<Product> getProducts() {
        return findAll();
    }

    public Product addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        save(product);
        return product;
    }

    public Product getProductById(UUID productId) {
            return findAll().stream()
                    .filter(product -> product.getId().equals(productId))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Product not found with ID:" + productId));

    }

    public Product updateProduct(UUID productId, String newName, double newPrice) {
        ArrayList<Product> products = findAll();
        Product foundproduct = products.stream()
                        .filter(product -> product.getId().equals(productId))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Product not found with ID:"+ productId));
        foundproduct.setName(newName);
        foundproduct.setPrice(newPrice);
        save(foundproduct);
        return foundproduct;
    }

    public void applyDiscount(double discount, ArrayList<UUID> productIds) {
        ArrayList<Product> products = findAll();
        products.forEach(product -> {
            if (productIds.contains(product.getId())) {
                double newPrice = product.getPrice() - (product.getPrice() * discount/100);
                product.setPrice(newPrice);
            }
        });
        saveAll(products);
    }

    public void deleteProductById(UUID productId) {
        ArrayList<Product> products = findAll();
        products.removeIf(product -> product.getId().equals(productId));
        saveAll(products);

    }
}
