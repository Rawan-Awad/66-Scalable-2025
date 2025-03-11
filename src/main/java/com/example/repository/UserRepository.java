package com.example.repository;

import com.example.model.Order;
import com.example.model.User;
import org.springframework.stereotype.Repository;

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

    public ArrayList<User> getUsers() {
        return findAll();
    }

    public User getUserById(UUID userId) {
        return findAll().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    public User addUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        save(user);
        return user;
    }

    public List<Order> getOrdersByUserId(UUID userId) {
        User user = getUserById(userId);
        if (user.getOrders().isEmpty()) {
            throw new RuntimeException("No orders found for user with ID: " + userId);
        }
        return user.getOrders();
    }

    public void addOrderToUser(UUID userId, Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }
        ArrayList<User> users = findAll();
        boolean userFound = false;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                user.getOrders().add(order);
                saveAll(users);
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    public void removeOrderFromUser(UUID userId, UUID orderId) {
        ArrayList<User> users = findAll();
        boolean userFound = false;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                boolean removed = user.getOrders().removeIf(order -> order.getId().equals(orderId));
                if (!removed) {
                    throw new RuntimeException("Order not found with ID: " + orderId);
                }
                saveAll(users);
                userFound = true;
                break;
            }
        }
        if (!userFound) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
    }

    public void deleteUserById(UUID userId) {
        ArrayList<User> users = findAll();
        boolean removed = users.removeIf(user -> user.getId().equals(userId));
        if (!removed) {
            throw new RuntimeException("User not found with ID: " + userId);
        }
        saveAll(users);
    }
}
