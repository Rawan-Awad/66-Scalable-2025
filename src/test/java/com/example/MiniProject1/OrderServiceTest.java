package com.example.MiniProject1;

import com.example.model.Order;
import com.example.model.Product;
import com.example.repository.OrderRepository;
import com.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Order order;
    private UUID orderId;

    @BeforeEach
    void setUp() {
        orderId = UUID.randomUUID();
        order = new Order();
        order.setId(orderId);
        order.setUserId(UUID.randomUUID());
    }

    // Test cases for addOrder()
    @Test
    void addOrder_ValidOrder_Success() {
        orderService.addOrder(order);
        verify(orderRepository, times(1)).addOrder(order);
    }

    @Test
    void addOrder_NullUserId_ThrowsException() {
        order.setUserId(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> orderService.addOrder(order));
        assertEquals("Order must have a user ID", exception.getMessage());
    }

    @Test
    void addOrder_CalculatesTotalPriceCorrectly() {
        List<Product> products = new ArrayList<>();
        products.add(new Product(UUID.randomUUID(), "Product1", 10.0));
        products.add(new Product(UUID.randomUUID(), "Product2", 20.0));
        order.setProducts(products);

        orderService.addOrder(order);
        assertEquals(30.0, order.getTotalPrice());
    }

    @Test
    void addOrder_EmptyProductList_TotalPriceZero() {
        order.setProducts(new ArrayList<>());
        orderService.addOrder(order);
        assertEquals(0.0, order.getTotalPrice());
    }

    @Test
    void getOrders_LargeDatasetHandledCorrectly() {
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            orders.add(new Order(UUID.randomUUID(), UUID.randomUUID()));
        }
        when(orderRepository.getOrders()).thenReturn(new ArrayList<>(orders));
        assertEquals(1000, orderService.getOrders().size());
    }

    @Test
    void getOrderById_ValidId_ReturnsOrder() {
        when(orderRepository.getOrderById(orderId)).thenReturn(order);
        assertEquals(order, orderService.getOrderById(orderId));
    }

    @Test
    void getOrderById_NullId_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> orderService.getOrderById(null));
        assertEquals("Order ID cannot be null", exception.getMessage());
    }

    @Test
    void getOrderById_InvalidId_ThrowsException() {
        when(orderRepository.getOrderById(orderId)).thenReturn(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> orderService.getOrderById(orderId));
        assertEquals("Order not found with ID: " + orderId, exception.getMessage());
    }

    @Test
    void deleteOrderById_ValidId_DeletesOrder() {
        when(orderRepository.getOrderById(orderId)).thenReturn(order);
        orderService.deleteOrderById(orderId);
        verify(orderRepository, times(1)).deleteOrderById(orderId);
    }

    @Test
    void deleteOrderById_NullId_ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> orderService.deleteOrderById(null));
        assertEquals("Order ID cannot be null", exception.getMessage());
    }

    @Test
    void deleteOrderById_InvalidId_ThrowsException() {
        when(orderRepository.getOrderById(orderId)).thenReturn(null);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> orderService.deleteOrderById(orderId));
        assertEquals("Order not found with ID: " + orderId, exception.getMessage());
    }

    @Test
    void getOrders_ReturnsEmptyList() {
        when(orderRepository.getOrders()).thenReturn(new ArrayList<>());
        assertTrue(orderService.getOrders().isEmpty());
    }

    @Test
    void getOrders_ReturnsMultipleOrders() {
        List<Order> orders = List.of(order, new Order(UUID.randomUUID(), UUID.randomUUID()));
        when(orderRepository.getOrders()).thenReturn(new ArrayList<>(orders));
        assertEquals(2, orderService.getOrders().size());
    }
}
