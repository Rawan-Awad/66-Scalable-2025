package com.example.repository;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class UserRepository extends MainRepository<User> {

    private static final String DATA_PATH = "src/main/java/com/example/data/users.json";

    public UserRepository() {
    }

    @Override
    protected String getDataPath() {
        return DATA_PATH;
    }

    @Override
    protected Class<User[]> getArrayType() {
        return User[].class;
    }

    // Get all users
    public ArrayList<User> getUsers() {
        return findAll();
    }

    // Get user by ID
    public User getUserById(UUID userId) {
        return findAll().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    // Add a new user
    public User addUser(User user) {
        save(user);
        return user;
    }

    // Get orders by user ID
    public List<Order> getOrdersByUserId(UUID userId) {
        User user = getUserById(userId);
        return (user != null) ? user.getOrders() : new ArrayList<>();
    }

    // Add order to user
    public void addOrderToUser(UUID userId, Order order) {
        ArrayList<User> users = findAll();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                user.getOrders().add(order);
                saveAll(users);
                return;
            }
        }
    }

    // Remove order from user
    public void removeOrderFromUser(UUID userId, UUID orderId) {
        ArrayList<User> users = findAll();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                user.getOrders().removeIf(order -> order.getId().equals(orderId));
                saveAll(users);
                return;
            }
        }
    }

    // Delete user by ID
    public void deleteUserById(UUID userId) {
        ArrayList<User> users = findAll();
        users.removeIf(user -> user.getId().equals(userId));
        saveAll(users);
    }
}
