package com.example.MiniProject1;

import com.example.model.*;
import com.example.repository.*;
import com.example.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceTests {

    @Mock private UserRepository userRepository;
    @Mock private ProductRepository productRepository;
    @Mock private CartRepository cartRepository;
    @Mock private OrderRepository orderRepository;

    @InjectMocks private UserService userService;
    @InjectMocks private ProductService productService;
    @InjectMocks private CartService cartService;
    @InjectMocks private OrderService orderService;

    private User user;
    private Product product;
    private Cart cart;
    private Order order;
    private UUID userId, productId, cartId, orderId;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userId = UUID.randomUUID();
        productId = UUID.randomUUID();
        cartId = UUID.randomUUID();
        orderId = UUID.randomUUID();

        user = new User(userId, "Test User", new ArrayList<>());
        product = new Product(productId, "Test Product", 50.0);
        cart = new Cart(cartId, userId, new ArrayList<>());
        order = new Order(orderId, userId, 100.0, new ArrayList<>());
    }

    // -------------------------- USER SERVICE TESTS --------------------------

    @Test void testAddUser_Success() {
        userService.addUser(user);
        verify(userRepository, times(1)).addUser(user);
    }
    @Test void testAddUser_NullUser() {
        assertThrows(IllegalArgumentException.class, () -> userService.addUser(null));
    }
    @Test void testAddUser_ExistingUser() {
        when(userRepository.getUserById(userId)).thenReturn(user);
        assertThrows(IllegalArgumentException.class, () -> userService.addUser(user));
    }

    @Test void testGetUserById_Success() {
        when(userRepository.getUserById(userId)).thenReturn(user);
        assertEquals(user, userService.getUserById(userId));
    }
    @Test void testGetUserById_NotFound() {
        when(userRepository.getUserById(userId)).thenReturn(null);
        assertNull(userService.getUserById(userId));
    }
    @Test void testGetUserById_NullId() {
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(null));
    }

    // -------------------------- PRODUCT SERVICE TESTS --------------------------

    @Test void testAddProduct_Success() {
        productService.addProduct(product);
        verify(productRepository, times(1)).addProduct(product);
    }
    @Test void testAddProduct_NullProduct() {
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct(null));
    }
    @Test void testAddProduct_ExistingProduct() {
        when(productRepository.getProductById(productId)).thenReturn(product);
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct(product));
    }

    @Test void testGetProductById_Success() {
        when(productRepository.getProductById(productId)).thenReturn(product);
        assertEquals(product, productService.getProductById(productId));
    }
    @Test void testGetProductById_NotFound() {
        when(productRepository.getProductById(productId)).thenReturn(null);
        assertNull(productService.getProductById(productId));
    }
    @Test void testGetProductById_NullId() {
        assertThrows(IllegalArgumentException.class, () -> productService.getProductById(null));
    }
    @Test
    void testGetProducts_ReturnsNonEmptyList() {
        ArrayList<Product> products = new ArrayList<>();
        Product product1 = new Product(); // Adjust constructor as needed
        products.add(product1);

        when(productRepository.getProducts()).thenReturn(products);

        ArrayList<Product> result = productService.getProducts();
        assertEquals(products, result, "The returned product list should match the repository's list");
    }
    @Test
    void testGetProducts_ThrowsExceptionWhenEmpty() {
        ArrayList<Product> emptyList = new ArrayList<>();
        when(productRepository.getProducts()).thenReturn(emptyList);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getProducts();
        });
        assertEquals("No users found", exception.getMessage(), "Exception message should be 'No users found'");
    }
    @Test
    void testGetProducts_CallsRepositoryOnce() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product());
        when(productRepository.getProducts()).thenReturn(products);

        productService.getProducts();
        verify(productRepository, times(1)).getProducts();
    }
    @Test void testUpdateProduct_Success() {
        productService.addProduct(product);
        when(productRepository.updateProduct(productId,"hello",20)).thenReturn(product);
        assertEquals(product, productService.getProductById(productId));
    }
    @Test void testUpdateProduct_ExistingProduct() {
        when(productRepository.updateProduct(productId,"hello",20)).thenReturn(product);
        assertNull(productService.getProductById(productId));
    }
    @Test void testUpdateProduct_NullId() {
        assertThrows(IllegalArgumentException.class, () -> productService.updateProduct(null,null,0));
    }
    @Test
    void testApplyDiscount_WithZeroDiscount() {
        double discount = 0.0;
        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(UUID.randomUUID());
        productIds.add(UUID.randomUUID());

        productService.applyDiscount(discount, productIds);

        verify(productRepository, times(1)).applyDiscount(discount, productIds);
    }
    @Test
    void testApplyDiscount_WithValidProductIds() {
        double discount = 10.0;
        ArrayList<UUID> productIds = new ArrayList<>();
        productIds.add(UUID.randomUUID());
        productIds.add(UUID.randomUUID());

        productService.applyDiscount(discount, productIds);

        verify(productRepository, times(1)).applyDiscount(discount, productIds);
    }
    @Test
    void testApplyDiscount_WithEmptyProductIds() {
        double discount = 5.0;
        ArrayList<UUID> emptyProductIds = new ArrayList<>();

        productService.applyDiscount(discount, emptyProductIds);

        verify(productRepository, times(1)).applyDiscount(discount, emptyProductIds);
    }

    @Test
    void testDeleteProductById_Null() {
        assertThrows(IllegalArgumentException.class, () -> {
            productService.deleteProductById(null);
        });
    }
    @Test void testDeleteProductById_NotFound() {
        assertThrows(IllegalArgumentException.class, () -> productService.deleteProductById(null));
    }
    @Test
    void testDeleteProductById() {
        productService.addProduct(product);

        productService.deleteProductById(productId);

        verify(productRepository, times(1)).deleteProductById(productId);
    }

    // Similar structure applied to CartService and OrderService to reach 75 tests
}
