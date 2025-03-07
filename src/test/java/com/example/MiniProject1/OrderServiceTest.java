package com.example.MiniProject1;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.example.model.Order;
import com.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.repository.OrderRepository;
import com.example.service.OrderService;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private UUID testOrderId;
    private UUID testUserId;
    private Order testOrder;
    private Product testProduct1;
    private Product testProduct2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        testOrderId = UUID.randomUUID();
        testUserId = UUID.randomUUID();
        
        testProduct1 = new Product();
        testProduct1.setId(UUID.randomUUID());
        testProduct1.setName("Test Product 1");
        testProduct1.setPrice(10.0);
        
        testProduct2 = new Product();
        testProduct2.setId(UUID.randomUUID());
        testProduct2.setName("Test Product 2");
        testProduct2.setPrice(15.0);
        
        List<Product> products = Arrays.asList(testProduct1, testProduct2);
        
        testOrder = new Order();
        testOrder.setId(testOrderId);
        testOrder.setUserId(testUserId);
        testOrder.setProducts(products);
        testOrder.setTotalPrice(25.0);
    }

    @Test
    void testAddOrder_Success() {
        // Arrange
        Order newOrder = new Order();
        newOrder.setUserId(testUserId);
        newOrder.setProducts(Arrays.asList(testProduct1, testProduct2));
        
        // Act
        orderService.addOrder(newOrder);
        
        // Assert
        verify(orderRepository, times(1)).addOrder(any(Order.class));
        assertEquals(25.0, newOrder.getTotalPrice());
    }

    @Test
    void testAddOrder_WithoutUserId_ThrowsException() {
        // Arrange
        Order newOrder = new Order();
        newOrder.setProducts(Arrays.asList(testProduct1, testProduct2));
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.addOrder(newOrder);
        });
        
        verify(orderRepository, never()).addOrder(any(Order.class));
    }

    @Test
    void testGetOrderById_Success() {
        // Arrange
        when(orderRepository.getOrderById(testOrderId)).thenReturn(testOrder);
        
        // Act
        Order result = orderService.getOrderById(testOrderId);
        
        // Assert
        assertNotNull(result);
        assertEquals(testOrderId, result.getId());
        assertEquals(testUserId, result.getUserId());
        assertEquals(25.0, result.getTotalPrice());
        
        verify(orderRepository, times(1)).getOrderById(testOrderId);
    }

    @Test
    void testGetOrderById_NotFound_ThrowsException() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(orderRepository.getOrderById(nonExistentId)).thenReturn(null);
        
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            orderService.getOrderById(nonExistentId);
        });
        
        verify(orderRepository, times(1)).getOrderById(nonExistentId);
    }

    @Test
    void testDeleteOrderById_Success() {
        // Arrange
        when(orderRepository.getOrderById(testOrderId)).thenReturn(testOrder);
        
        // Act
        orderService.deleteOrderById(testOrderId);
        
        // Assert
        verify(orderRepository, times(1)).getOrderById(testOrderId);
        verify(orderRepository, times(1)).deleteOrderById(testOrderId);
    }
}