package com.abdullah.shopping_cart_service.service;

import com.abdullah.shopping_cart_service.model.*;
import com.abdullah.shopping_cart_service.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;



@Service
@Transactional
public class CartService {

    private final CartRepository cartRepo;
    private final ProductRepository productRepo;

    public CartService(CartRepository cartRepo, ProductRepository productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }
    public ShoppingCart saveCart(ShoppingCart cart) {
        return cartRepo.save(cart);
    }
    public ShoppingCart addItemToCart(Long cartId, Long productId, int quantity) {
        ShoppingCart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (product.getStockQty() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        product.setStockQty(product.getStockQty() - quantity);
        CartItem item = new CartItem();
        item.setCart(cart);
        item.setProduct(product);
        item.setQuantity(quantity);
        item.setPriceSnapshot(product.getPrice());
        item.setTaxSnapshot(product.getTaxRate());

        cart.getItems().add(item);

        return cartRepo.save(cart);
    }

    public ShoppingCart payForCart(Long cartId) {
        ShoppingCart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getStatus() == CartStatus.PAID) {
            throw new RuntimeException("Cart already paid");
        }

        BigDecimal total = BigDecimal.ZERO;
        BigDecimal totalTax = BigDecimal.ZERO;

        for (CartItem item : cart.getItems()) {
            BigDecimal itemPrice = item.getPriceSnapshot()
                    .multiply(BigDecimal.valueOf(item.getQuantity()));
            BigDecimal itemTax = itemPrice.multiply(item.getTaxSnapshot());

            total = total.add(itemPrice);
            totalTax = totalTax.add(itemTax);
        }

        cart.setTotalPrice(total);
        cart.setTotalTax(totalTax);
        cart.setStatus(CartStatus.PAID);

        return cartRepo.save(cart);
    }
    public ShoppingCart getCart(Long cartId) {
        return cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
    public ShoppingCart removeItemFromCart(Long cartId, Long productId) {
        ShoppingCart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        boolean removed = cart.getItems().removeIf(item -> item.getProduct().getId().equals(productId));

        if (!removed) {
            throw new RuntimeException("Item not found in cart");
        }

        return cartRepo.save(cart);
    }
    public ShoppingCart emptyCart(Long cartId) {
        ShoppingCart cart = cartRepo.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().clear();
        return cartRepo.save(cart);
    }
}