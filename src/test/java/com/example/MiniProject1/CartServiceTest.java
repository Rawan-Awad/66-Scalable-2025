//package com.example.MiniProject1;
//
//
//import com.example.model.Cart;
//import com.example.model.Product;
//import com.example.repository.CartRepository;
//import com.example.service.CartService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class CartServiceTest {
//
//    @Mock
//    private CartRepository cartRepository;
//
//    @InjectMocks
//    private CartService cartService;
//
//    private Cart testCart;
//    private UUID cartId;
//    private UUID userId;
//    private Product testProduct;
//
//    @BeforeEach
//    void setUp() {
//        cartId = UUID.randomUUID();
//        userId = UUID.randomUUID();
//        testProduct = new Product(UUID.randomUUID(), "Test Product", 10.0);
//
//        testCart = new Cart(cartId, userId, new ArrayList<>());
//    }
//
//    // ✅ Test adding a new cart
////    @Test
////    void testAddCart() {
////        when(cartRepository.addCart(any(Cart.class))).thenReturn(testCart);
////
////        Cart result = cartService.addCart(testCart);
////
////        assertNotNull(result);
////        assertEquals(testCart.getId(), result.getId());
////        verify(cartRepository, times(1)).addCart(testCart);
////    }
//
//    // ✅ Test retrieving all carts
//    @Test
//    void testGetCarts() {
//        List<Cart> carts = List.of(testCart);
//        when(cartRepository.getCarts()).thenReturn(new ArrayList<>(carts));
//
//        ArrayList<Cart> result = cartService.getCarts();
//
//        assertNotNull(result);
//        assertFalse(result.isEmpty());
//        assertEquals(1, result.size());
//        verify(cartRepository, times(1)).getCarts();
//    }
//
//    // ✅ Test retrieving a cart by ID (exists)
//    @Test
//    void testGetCartById_Found() {
//        when(cartRepository.getCartById(cartId)).thenReturn(testCart);
//
//        Cart result = cartService.getCartById(cartId);
//
//        assertNotNull(result);
//        assertEquals(cartId, result.getId());
//        verify(cartRepository, times(1)).getCartById(cartId);
//    }
//
//    // ✅ Test retrieving a cart by ID (not found)
//    @Test
//    void testGetCartById_NotFound() {
//        when(cartRepository.getCartById(cartId)).thenReturn(null);
//
//        Cart result = cartService.getCartById(cartId);
//
//        assertNull(result);
//        verify(cartRepository, times(1)).getCartById(cartId);
//    }
//
//    // ✅ Test retrieving a cart by user ID
//    @Test
//    void testGetCartByUserId() {
//        when(cartRepository.getCartByUserId(userId)).thenReturn(testCart);
//
//        Cart result = cartService.getCartByUserId(userId);
//
//        assertNotNull(result);
//        assertEquals(userId, result.getUserId());
//        verify(cartRepository, times(1)).getCartByUserId(userId);
//    }
//
//    // ✅ Test adding a product to a cart
//    @Test
//    void testAddProductToCart() {
//        when(cartRepository.getCartById(cartId)).thenReturn(testCart);
//
//        cartService.addProductToCart(cartId, testProduct);
//
//        assertTrue(testCart.getProducts().contains(testProduct));
//        verify(cartRepository, times(1)).getCartById(cartId);
//        verify(cartRepository, times(1)).saveAll(any());
//    }
//
//    // ✅ Test deleting a product from a cart
////    @Test
////    void testDeleteProductFromCart() {
////        testCart.addProduct(testProduct);
////        when(cartRepository.getCartById(cartId)).thenReturn(testCart);
////
////        cartService.deleteProductFromCart(cartId, testProduct);
////
////        assertFalse(testCart.getProducts().contains(testProduct));
////        verify(cartRepository, times(1)).getCartById(cartId);
////        verify(cartRepository, times(1)).saveAll(any());
////    }
//
//    // ✅ Test deleting a cart by ID
//    @Test
//    void testDeleteCartById() {
//        doNothing().when(cartRepository).deleteCartById(cartId);
//
//        cartService.deleteCartById(cartId);
//
//        verify(cartRepository, times(1)).deleteCartById(cartId);
//    }
//}
