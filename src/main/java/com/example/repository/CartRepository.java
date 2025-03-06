package com.example.repository;

import org.springframework.stereotype.Repository;

import com.example.models.Cart;
import com.example.models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class CartRepository extends MainRepository<Cart> {
    public static List<Cart> carts = new ArrayList<>();

    public CartRepository() {
    }

    public Cart addCart(Cart cart) {
        // Implementation to be provided
        return null;
    }

    public ArrayList<Cart> getCarts() {
        // Implementation to be provided
        return null;
    }

    public Cart getCartById(UUID cartId) {
        // Implementation to be provided
        return null;
    }

    public Cart getCartByUserId(UUID userId) {
        // Implementation to be provided
        return null;
    }

    public void addProductToCart(UUID cartId, Product product) {
        // Implementation to be provided
    }

    public void deleteProductFromCart(UUID cartId, Product product) {
        // Implementation to be provided
    }

    public void deleteCartById(UUID cartId) {
        // Implementation to be provided
    }

    @Override
    protected String getDataPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataPath'");
    }

    @Override
    protected Class<Cart[]> getArrayType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArrayType'");
    }
}