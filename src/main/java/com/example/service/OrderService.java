package com.example.service;

import com.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if (order.getUserId() == null) {
            throw new IllegalArgumentException("Order must have a user ID");
        }
        
        if (order.getProducts() != null && !order.getProducts().isEmpty()) {
            double totalPrice = order.getProducts().stream()
                    .mapToDouble(product -> product.getPrice())
                    .sum();
            order.setTotalPrice(totalPrice);
        }
        
        orderRepository.addOrder(order);
    }

    public ArrayList<Order> getOrders() {
        return orderRepository.getOrders();
    }

    public Order getOrderById(UUID orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        
        Order order = orderRepository.getOrderById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
        
        return order;
    }


    public void deleteOrderById(UUID orderId) throws IllegalArgumentException {
        if (orderId == null) {
            throw new IllegalArgumentException("Order ID cannot be null");
        }
        
        Order order = orderRepository.getOrderById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found with ID: " + orderId);
        }
        
        orderRepository.deleteOrderById(orderId);
    }
}