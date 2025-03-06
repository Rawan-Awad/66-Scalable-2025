package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.Order;
import com.example.repository.OrderRepository;

import java.util.ArrayList;
import java.util.UUID;

@Service
@SuppressWarnings("rawtypes")
public class OrderService extends MainService<Order> {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void addOrder(Order order) {
        // Implementation to be provided
    }

    public ArrayList<Order> getOrders() {
        // Implementation to be provided
        return null;
    }

    public Order getOrderById(UUID orderId) {
        // Implementation to be provided
        return null;
    }

    public void deleteOrderById(UUID orderId) throws IllegalArgumentException {
        // Implementation to be provided
    }
}