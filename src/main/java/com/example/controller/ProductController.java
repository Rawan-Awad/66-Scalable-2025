package com.example.controller;

import com.example.model.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/product")

public class ProductController {
    ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/")
    public ArrayList<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{productId}")
    public Product getProductById(@PathVariable UUID productId) {
        return productService.getProductById(productId);
    }

    @PutMapping("/update/{productId}")
    public Product updateProduct(@PathVariable UUID productId, @RequestBody(required = false) Map<String, Object> body) {
        if (body == null) {
            System.out.println("Error: Empty request body received.");
            return null;
        }

        if (!body.containsKey("newName") || !body.containsKey("newPrice")) {
            System.out.println("Error: Missing required fields (newName, newPrice)");
            return null;
        }

        String newName = body.get("newName").toString();
        double newPrice;

        try {
            newPrice = Double.parseDouble(body.get("newPrice").toString());
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid price format");
            return null;
        }

        return productService.updateProduct(productId, newName, newPrice);
    }





    @PutMapping("/applyDiscount")
    public String applyDiscount(@RequestParam double discount, @RequestBody ArrayList<UUID> productIds) {
        try {
            productService.applyDiscount(discount, productIds);
            return "Discount applied successfully";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProductById(@PathVariable UUID productId) {
        try {
            productService.deleteProductById(productId);
            return "Product deleted successfully";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        }
    }
}

