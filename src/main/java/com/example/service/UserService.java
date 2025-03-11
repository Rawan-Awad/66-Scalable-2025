package com.example.service;

import com.example.model.Order;
import com.example.model.User;
import com.example.model.Cart;
import com.example.model.Product;
import com.example.repository.OrderRepository;
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
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if(userRepository.getUserById(user.getId()) != null) {
            throw new IllegalArgumentException("User already exists");
        }
        return userRepository.addUser(user);
    }

    // Get all users
    public ArrayList<User> getUsers() {
        ArrayList<User> users = userRepository.getUsers();
        if (users.isEmpty()) {
            throw new RuntimeException("No users found");
        }
        return users;
    }

    // Get a specific user by ID
    public User getUserById(UUID userId) {
        if(userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }
        return userRepository.getUserById(userId);
    }

    // Get user’s orders
    public List<Order> getOrdersByUserId(UUID userId) {
        List<Order> orders = userRepository.getOrdersByUserId(userId);
        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found for user with ID: " + userId);
        }
        return orders;
    }

    // Add a new order (checkout process)
    public void addOrderToUser(UUID userId) {
        Cart userCart = cartService.getCartByUserId(userId);
        if (userCart == null || userCart.getProducts().isEmpty()) {
            throw new RuntimeException("Cart is empty. Cannot proceed with checkout.");
        }
        double totalPrice = userCart.getProducts().stream().mapToDouble(Product::getPrice).sum();
        Order newOrder = new Order(UUID.randomUUID(), userId, totalPrice, userCart.getProducts());
        orderRepository.addOrder(newOrder);
        userRepository.addOrderToUser(userId, newOrder);
        cartService.deleteCartById(userId);
    }

    // Empty user’s cart
    public void emptyCart(UUID userId) {
        if (cartService.getCartByUserId(userId) == null) {
            throw new RuntimeException("Cart not found for user with ID: " + userId);
        }
        cartService.deleteCartById(userId);
    }

    // Remove order from user
    public void removeOrderFromUser(UUID userId, UUID orderId) {
        List<Order> orders = userRepository.getOrdersByUserId(userId);
        boolean removed = orders.removeIf(order -> order.getId().equals(orderId));
        if (!removed) {
            throw new RuntimeException("Order not found with ID: " + orderId);
        }
        userRepository.removeOrderFromUser(userId, orderId);
    }

    // Delete user by ID
    public void deleteUserById(UUID userId) {
        if (userRepository.getUserById(userId) == null) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        userRepository.deleteUserById(userId);
    }
}
