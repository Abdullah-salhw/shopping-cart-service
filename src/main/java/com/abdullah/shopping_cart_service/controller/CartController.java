package com.abdullah.shopping_cart_service.controller;

import com.abdullah.shopping_cart_service.dto.AddItemRequest;
import com.abdullah.shopping_cart_service.model.ShoppingCart;
import com.abdullah.shopping_cart_service.model.CartStatus;
import com.abdullah.shopping_cart_service.service.CartService;
import org.springframework.web.bind.annotation.*;
import com.abdullah.shopping_cart_service.dto.CartItemDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ShoppingCart createCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setStatus(CartStatus.NEW);
        cart.setItems(new ArrayList<>());
        return cartService.saveCart(cart);
    }

    @PostMapping("/{cartId}/items")
    public ShoppingCart addItemToCart(
            @PathVariable Long cartId,
            @RequestBody AddItemRequest request
    ) {
        return cartService.addItemToCart(cartId, request.getProductId(), request.getQuantity());
    }


    @PostMapping("/{cartId}/pay")
    public ResponseEntity<?> payForCart(@PathVariable Long cartId) {
        try {
            ShoppingCart cart = cartService.payForCart(cartId);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException ex) {
            if (ex.getMessage().equals("Cart already paid")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
            } else if (ex.getMessage().equals("Cart not found")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
            }
        }
    }

    @GetMapping("/{cartId}")
    public ShoppingCart getCart(@PathVariable Long cartId) {
        return cartService.getCart(cartId);
    }

    @GetMapping("/{cartId}/items")
    public List<CartItemDTO> getCartItems(@PathVariable Long cartId) {
        ShoppingCart cart = cartService.getCart(cartId);

        return cart.getItems().stream()
                .map(item -> new CartItemDTO(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getQuantity()
                ))
                .collect(Collectors.toList());
    }
    @DeleteMapping("/{cartId}/items/{productId}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long productId) {
        try {
            ShoppingCart cart = cartService.removeItemFromCart(cartId, productId);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
    @DeleteMapping("/{cartId}/items")
    public ResponseEntity<?> emptyCart(@PathVariable Long cartId) {
        try {
            ShoppingCart cart = cartService.emptyCart(cartId);
            return ResponseEntity.ok(cart);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
    }
}