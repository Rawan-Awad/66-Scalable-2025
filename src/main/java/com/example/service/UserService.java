package com.example.service;

import com.example.model.User;
import com.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class UserService extends MainService<User> {

    private final UserRepository userRepository;
    private final CartService cartService;
    private final OrderRepository orderRepository;

    // Constructor for Dependency Injection
    public UserService(UserRepository userRepository, CartService cartService, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.cartService = cartService;
        this.orderRepository = orderRepository;
    }

    // Add a new user
    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    // Get all users
    public ArrayList<User> getUsers() {
        return userRepository.getUsers();
    }

    // Get a specific user by ID
    public User getUserById(UUID userId) {
        return userRepository.getUserById(userId);
    }

    // Get user’s orders
    public List<Order> getOrdersByUserId(UUID userId) {
        return userRepository.getOrdersByUserId(userId);
    }

    // Add a new order (checkout process)
//    public void addOrderToUser(UUID userId) {
//        Cart userCart = cartService.getCartByUserId(userId);
//        if (userCart == null || userCart.getProducts().isEmpty()) {
//            throw new RuntimeException("Cart is empty. Cannot proceed with checkout.");
//        }
//        double totalPrice = userCart.getProducts().stream().mapToDouble(Product::getPrice).sum();
//        Order newOrder = new Order(UUID.randomUUID(), userId, totalPrice, userCart.getProducts());
//        orderRepository.addOrder(newOrder);
//        userRepository.addOrderToUser(userId, newOrder);
//        cartService.emptyCart(userId);
//    }
//
//    // Empty user’s cart
//    public void emptyCart(UUID userId) {
//        cartService.emptyCart(userId);
//    }

    // Remove order from user
    public void removeOrderFromUser(UUID userId, UUID orderId) {
        userRepository.removeOrderFromUser(userId, orderId);
    }

    // Delete user by ID
    public void deleteUserById(UUID userId) {
        userRepository.deleteUserById(userId);
    }
}
