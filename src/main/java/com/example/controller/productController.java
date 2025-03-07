package com.example.controller;

import com.example.models.Product;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/product")

public class productController {
    ProductService productService;

    @Autowired
    public productController(ProductService productService) {this.productService = productService;}

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
    public Product updateProduct(@PathVariable UUID productId, @RequestBody Map<String, Object> body) {
        String newName = (String) body.get("name");
        double newPrice = Double.parseDouble(body.get("price").toString());
        return productService.updateProduct(productId,newName,newPrice);
    }

    @PutMapping("/applyDiscount")
    public void applyDiscount(@RequestParam double discount,@RequestBody ArrayList<UUID> productIds){
        productService.applyDiscount(discount,productIds);
    }

    @DeleteMapping("/delete/{productId}")
    public void deleteProductById(@PathVariable UUID productId) {
        productService.deleteProductById(productId);
    }

}
