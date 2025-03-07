package com.example.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.models.Order;
import com.example.service.OrderService;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/")
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable UUID orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/")
    public ArrayList<Order> getOrders() {
        return orderService.getOrders();
    }

    @DeleteMapping("/delete/{orderId}")
    public String deleteOrderById(@PathVariable UUID orderId) {
        try {
            orderService.deleteOrderById(orderId);
            return "Order with ID: " + orderId + " deleted successfully";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}