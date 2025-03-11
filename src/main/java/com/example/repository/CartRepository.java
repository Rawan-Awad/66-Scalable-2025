package com.example.repository;

import com.example.model.Cart;
import com.example.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class CartRepository extends MainRepository<Cart> {

    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/carts.json"; // Path to the JSON file
    }

    @Override
    protected Class<Cart[]> getArrayType() {
        return Cart[].class;
    }

    // Add a new cart
    public Cart addCart(Cart cart) {
        System.out.println("➡️ [CartRepository] Saving cart: " + cart.getId());
        save(cart);
        System.out.println("✅ [CartRepository] Cart saved.");
        return cart;
    }



    // Get all carts
    public ArrayList<Cart> getCarts() {
        return findAll(); // Using findAll() from MainRepository
    }

    // Get a cart by ID
    public Cart getCartById(UUID cartId) {
        return getCarts().stream()
                .filter(cart -> cart.getId().equals(cartId))
                .findFirst()
                .orElse(null);
    }

    // Get a cart by User ID
    public Cart getCartByUserId(UUID userId) {
        return getCarts().stream()
                .filter(cart -> cart.getUserId().equals(userId))
                .findFirst()
                .orElse(null);
    }


    public void addProductToCart(UUID cartId, Product product) {
        ArrayList<Cart> carts = findAll(); // Load all carts
        for (Cart cart : carts) {
            if (cart.getId().equals(cartId)) {
                cart.addProduct(product);
                saveAll(carts); // ✅ Save updated list of carts
                return;
            }
        }
        throw new RuntimeException("Cart not found");
    }

    public void deleteProductFromCart(UUID cartId, Product product) {
        ArrayList<Cart> carts = findAll(); // Load all carts
        for (Cart cart : carts) {
            if (cart.getId().equals(cartId)) {
                cart.removeProduct(product);
                saveAll(carts); // ✅ Save updated list of carts
                return;
            }
        }
        throw new RuntimeException("Cart not found");
    }

    // Delete a product from the cart
//    public void deleteProductFromCart(UUID cartId, Product product) {
//        Cart cart = getCartById(cartId);
//        if (cart != null) {
//            cart.removeProduct(product);
//            saveAll(getCarts()); // Save updated list
//        }
//    }

    // Delete a cart by ID
    public void deleteCartById(UUID cartId) {
        ArrayList<Cart> carts = getCarts();
        carts.removeIf(cart -> cart.getId().equals(cartId));
        saveAll(carts); // Save updated list
    }
}
