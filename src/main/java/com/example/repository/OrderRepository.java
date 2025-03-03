package com.example.repository;


import org.springframework.stereotype.Repository;

import com.example.models.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@SuppressWarnings("rawtypes")
public class OrderRepository extends MainRepository<Order> {
    public static List<Order> orders = new ArrayList<>();

    public OrderRepository() {
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

    public void deleteOrderById(UUID orderId) {
        // Implementation to be provided
    }

    @Override
    protected String getDataPath() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getDataPath'");
    }

    @Override
    protected Class<Order[]> getArrayType() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArrayType'");
    }
}