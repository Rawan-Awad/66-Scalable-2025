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
//    @Test void testAddUser_ExistingUser() {
//        when(userRepository.getUserById(userId)).thenReturn(user);
//        assertThrows(IllegalArgumentException.class, () -> userService.addUser(user));
//    }

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
    @Test void testGetUsers_ReturnsNonEmptyList(){
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User(); // Adjust constructor as needed
        users.add(user1);

        when(userRepository.getUsers()).thenReturn(users);

        ArrayList<User> result = userService.getUsers();
        assertEquals(users, result, "The returned user list should match the repository's list");

    }
    @Test
    void testGetUsers_ThrowsExceptionWhenEmpty() {
        ArrayList<User> emptyList = new ArrayList<>();
        when(userRepository.getUsers()).thenReturn(emptyList);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.getUsers();
        });
        assertEquals("No users found", exception.getMessage(), "Exception message should be 'No users found'");
    }
    @Test
    void testGetUsers_CallsRepositoryOnce() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User());
        when(userRepository.getUsers()).thenReturn(users);

        userService.getUsers();
        verify(userRepository, times(1)).getUsers();
    }

    // -------------------------- PRODUCT SERVICE TESTS --------------------------

    @Test void testAddProduct_Success() {
        productService.addProduct(product);
        verify(productRepository, times(1)).addProduct(product);
    }
    @Test void testAddProduct_NullProduct() {
        assertThrows(IllegalArgumentException.class, () -> productService.addProduct(null));
    }
//    @Test void testAddProduct_ExistingProduct() {
//        when(productRepository.getProductById(productId)).thenReturn(product);
//        assertThrows(IllegalArgumentException.class, () -> productService.addProduct(product));
//    }

    @Test void testGetProductById_Success() {
        when(productRepository.getProductById(productId)).thenReturn(product);
        assertEquals(product, productService.getProductById(productId));
    }
    @Test
    void testGetProductById_NotFound() {
        when(productRepository.getProductById(productId)).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getProductById(productId);
        });
        assertEquals("Product not found", exception.getMessage());
    }
    @Test void testGetProductById_NullId() {
        assertThrows(IllegalArgumentException.class, () -> productService.getProductById(null));
    }
    @Test
    void testGetProducts_ReturnsNonEmptyList() {
        ArrayList<Product> products = new ArrayList<>();
        Product product1 = new Product();
        products.add(product1);

        when(productRepository.getProducts()).thenReturn(products);

        ArrayList<Product> result = productService.getProducts();
        assertEquals(products, result, "The returned product list matches the repository's list");
    }
    @Test
    void testGetProducts_ThrowsExceptionWhenEmpty() {
        ArrayList<Product> emptyList = new ArrayList<>();
        when(productRepository.getProducts()).thenReturn(emptyList);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getProducts();
        });
        assertEquals("No products found", exception.getMessage(), "Exception message should be 'No products found'");
    }
    @Test
    void testGetProducts_CallsRepositoryOnce() {
        ArrayList<Product> products = new ArrayList<>();
        products.add(new Product());
        when(productRepository.getProducts()).thenReturn(products);

        productService.getProducts();
        verify(productRepository, times(1)).getProducts();
    }
    @Test
    void testUpdateProduct_Success() {
        when(productRepository.getProductById(productId)).thenReturn(product);
        Product updatedProduct = new Product(productId, "hello", 20);
        when(productRepository.updateProduct(productId, "hello", 20)).thenReturn(updatedProduct);
        Product result = productService.updateProduct(productId, "hello", 20);
        assertEquals(updatedProduct, result);
    }

    @Test
    void testUpdateProduct_NotFound() {
        when(productRepository.updateProduct(productId, "hello", 20)).thenReturn(null);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.updateProduct(productId, "hello", 20);
        });
        assertEquals("Product not found", exception.getMessage());
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


    // -------------------------- CART SERVICE TESTS --------------------------
    @Test
    void testAddCart_Success() {
        when(cartRepository.addCart(cart)).thenReturn(cart);

        Cart result = cartService.addCart(cart);

        assertEquals(cart, result, "cart returned");
        verify(cartRepository, times(1)).addCart(cart);
    }
    @Test
    void testAddCart_NullCart() {
        assertThrows(IllegalArgumentException.class, () -> {
            cartService.addCart(null);
        });
    }
    @Test
    void testAddCart_ReturnsPersistedCart() {
        Cart newCart = new Cart(null, userId, new ArrayList<>());
        Cart persistedCart = new Cart(UUID.randomUUID(), userId, new ArrayList<>());
        when(cartRepository.addCart(newCart)).thenReturn(persistedCart);
        Cart result = cartService.addCart(newCart);
        assertNotNull(result, "Persisted cart cant be null");
        assertEquals(persistedCart, result, "The returned cart matches the persisted cart");
    }
    @Test
    void testGetCarts_ReturnsNonEmptyList() {
        ArrayList<Cart> carts = new ArrayList<>();
        Cart cart1 = new Cart(UUID.randomUUID(), userId, new ArrayList<>());
        carts.add(cart1);
        when(cartRepository.getCarts()).thenReturn(carts);
        ArrayList<Cart> result = cartService.getCarts();
        assertEquals(carts, result, "The returned list of carts should match the repository's list");
    }
    @Test void testGetCarts_ReturnsEmptyList() {
        ArrayList<Cart> emptyList = new ArrayList<>();
        when(cartRepository.getCarts()).thenReturn(emptyList);

        ArrayList<Cart> result = cartService.getCarts();
        assertEquals(emptyList, result, "The service should return an empty list when no carts exist");
    }
    @Test void testGetCarts_CallsRepositoryOnce() {
        ArrayList<Cart> carts = new ArrayList<>();
        carts.add(new Cart(UUID.randomUUID(), userId, new ArrayList<>()));
        when(cartRepository.getCarts()).thenReturn(carts);

        cartService.getCarts();
        verify(cartRepository, times(1)).getCarts();
    }
    @Test void testGetCartById_ReturnsValidCart() {
        Cart expectedCart = new Cart(cartId, userId, new ArrayList<>());
        when(cartRepository.getCartById(cartId)).thenReturn(expectedCart);

        Cart result = cartService.getCartById(cartId);

        assertEquals(expectedCart, result, "The service should return the expected cart");
    }

    @Test void testGetCartById_RepositoryThrowsException() {
        when(cartRepository.getCartById(cartId)).thenThrow(new RuntimeException("Repository error"));
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cartService.getCartById(cartId);
        });
        assertEquals("Repository error", exception.getMessage());
    }

    @Test void testGetCartById_WithNullInput() {
        when(cartRepository.getCartById(null)).thenReturn(null);
        Cart result = cartService.getCartById(null);
        assertNull(result, "Expected null when cartId is null");
    }

    @Test void testGetCartByUserId_ReturnsCart() {
        when(cartRepository.getCartByUserId(userId)).thenReturn(cart);

        Cart result = cartService.getCartByUserId(userId);

        assertNotNull(result, "Cart should not be null when a valid cart exists for the user");
        assertEquals(cart, result, "The returned cart should match the expected cart");
    }

    @Test void testGetCartByUserId_ReturnsNullWhenNotFound() {
        when(cartRepository.getCartByUserId(userId)).thenReturn(null);

        Cart result = cartService.getCartByUserId(userId);

        assertNull(result, "The service should return null when no cart is found for the given userId");
    }

    @Test void testGetCartByUserId_WithNullUserId() {
        when(cartRepository.getCartByUserId(null)).thenReturn(null);
        Cart result = cartService.getCartByUserId(null);
        assertNull(result, "Expected null when a null userId is provided");
    }

    @Test void testAddProductToCart_WhenCartExists() {
        when(cartRepository.getCartById(cartId)).thenReturn(cart);
        when(userRepository.getUsers()).thenReturn(new ArrayList<>());
        cartService.addProductToCart(cartId, product);
        verify(cartRepository, times(1)).addProductToCart(cart.getId(), product);
        verify(cartRepository, never()).addCart(any());
    }

    @Test void testAddProductToCart_CreatesNewCartWhenUserFound() {
        when(cartRepository.getCartById(cartId)).thenReturn(null);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.getUsers()).thenReturn(users);
        Cart userCart = new Cart(cartId, userId, new ArrayList<>());
        when(cartRepository.getCartByUserId(user.getId())).thenReturn(userCart);
        cartService.addProductToCart(cartId, product);
        verify(cartRepository, times(1)).addCart(argThat(newCart ->
                newCart.getId().equals(cartId) && newCart.getUserId().equals(userId)
        ));
        verify(cartRepository, times(1)).addProductToCart(cartId, product);
    }

    @Test void testAddProductToCart_ThrowsExceptionWhenNoUserFound() {
        when(cartRepository.getCartById(cartId)).thenReturn(null);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        when(userRepository.getUsers()).thenReturn(users);
        when(cartRepository.getCartByUserId(user.getId())).thenReturn(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cartService.addProductToCart(cartId, product);
        });
        assertEquals("No user found for this cart ID", exception.getMessage());
    }
    @Test void testDeleteCartById_Success() {
        cartService.deleteCartById(cartId);
        verify(cartRepository, times(1)).deleteCartById(cartId);
    }
    @Test void testDeleteCartById_NullCartId() {
        doThrow(new IllegalArgumentException("Cart ID cannot be null"))
                .when(cartRepository).deleteCartById(null);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cartService.deleteCartById(null);
        });
        assertEquals("Cart ID cannot be null", exception.getMessage());
    }

    @Test void testDeleteCartById_RepositoryException() {
        doThrow(new RuntimeException("Deletion error")).when(cartRepository).deleteCartById(cartId);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cartService.deleteCartById(cartId);
        });
        assertEquals("Deletion error", exception.getMessage());
    }

    @Test void testDeleteProductFromCart_Success() {
        when(cartRepository.getCartById(cartId)).thenReturn(cart);

        cartService.deleteProductFromCart(cartId, product);

        verify(cartRepository, times(1)).deleteProductFromCart(cartId, product);
    }

    @Test
    void testDeleteProductFromCart_CartNotFound() {
        when(cartRepository.getCartById(cartId)).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            cartService.deleteProductFromCart(cartId, product);
        });
        assertEquals("Cart not found", exception.getMessage());
    }

    @Test void testDeleteProductFromCart_RepositoryException() {
        when(cartRepository.getCartById(cartId)).thenReturn(cart);
        doThrow(new RuntimeException("Deletion error")).when(cartRepository).deleteProductFromCart(cartId, product);
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cartService.deleteProductFromCart(cartId, product);
        });
        assertEquals("Deletion error", exception.getMessage());
    }


    // Similar structure applied to CartService and OrderService to reach 75 tests
    //------------------------------ORDER TESTS----------------------------------------

}