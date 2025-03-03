package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.models.Order;
import com.example.models.User;

@Repository
@SuppressWarnings("rawtypes")
public class UserRepository extends MainRepository<User> {
    public static List<User> users = new ArrayList<>();

    public UserRepository() {
    }

    public ArrayList<User> getUsers() {
        // Implementation to be provided
        return null;
    }

    public User getUserById(UUID userId) {
        // Implementation to be provided
        return null;
    }

    public User addUser(User user) {
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

    public void removeOrderFromUser(UUID userId, UUID orderId) {
        // Implementation to be provided
    }

    public void deleteUserById(UUID userId) {
        // Implementation to be provided
    }

    @Override
    protected String getDataPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataPath'");
    }

    @Override
    protected Class<User[]> getArrayType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArrayType'");
    }
}
