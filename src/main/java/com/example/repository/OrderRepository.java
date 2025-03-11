package com.example.repository;

import com.example.model.Order;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class OrderRepository extends MainRepository<Order> {

    @Override
    protected String getDataPath() {
        return "src/main/java/com/example/data/orders.json"; // Path to the JSON file
    }

    @Override
    protected Class<Order[]> getArrayType() {
        return Order[].class;
    }

    // Add a new order
    public void addOrder(Order order) {
        save(order);
    }

    // Get all orders
    public ArrayList<Order> getOrders() {
        return findAll();
    }

    // Get a specific order by ID
    public Order getOrderById(UUID orderId) {
        return getOrders().stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    // Delete a specific order by ID
    public void deleteOrderById(UUID orderId) {
        ArrayList<Order> orders = getOrders();
        orders.removeIf(order -> order.getId().equals(orderId));
        saveAll(orders);
    }
}
