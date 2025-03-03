package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Order;
import com.example.models.User;
import com.example.repository.CartRepository;
import com.example.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class UserService extends MainService<User> {
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Autowired
    public UserService(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    public User addUser(User user) {
        // Implementation to be provided
        return null;
    }

    public ArrayList<User> getUsers() {
        // Implementation to be provided
        return null;
    }

    public User getUserById(UUID userId) {
        // Implementation to be provided
        return null;
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        // Implementation to be provided
        return null;
    }

    public void addOrderToUser(UUID userId, Order order) {
        // Implementation to be provided
    }

    public void emptyCart(UUID userId) {
        // Implementation to be provided
    }

    public void removeOrderFromUser(UUID userId, UUID orderId) {
        // Implementation to be provided
    }

    public void deleteUserById(UUID userId) {
        // Implementation to be provided
    }
}
