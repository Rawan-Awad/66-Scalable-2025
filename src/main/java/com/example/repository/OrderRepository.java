package com.example.repository;


import org.springframework.stereotype.Repository;

import com.example.models.Order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class OrderRepository extends MainRepository<Order> {
    public static List<Order> orders = new ArrayList<>();

    public OrderRepository() {
        try {
            orders = this.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void addOrder(Order order) {
        if (order.getId() == null) {
            order.setId(UUID.randomUUID());
        }
        // Add to in-memory list
        orders.add(order);
        // Save to file
        save(order);
    }

    public ArrayList<Order> getOrders() {
        // Refresh orders from file
        orders = findAll();
        return new ArrayList<>(orders);
    }
    public Order getOrderById(UUID orderId) {
        // Refresh orders from file
        orders = findAll();
        
        return orders.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst()
                .orElse(null);
    }

    public void deleteOrderById(UUID orderId) {
        // Refresh orders from file
        orders = findAll();
        boolean removed = orders.removeIf(order -> order.getId().equals(orderId));
        
        if (removed) {
            // Save updated list to file
            saveAll(new ArrayList<>(orders));
        }
    }

    @Override
    protected String getDataPath() {
        return "data/orders.json";
    }

    @Override
    protected Class<Order[]> getArrayType() {
        return Order[].class;
    }
}