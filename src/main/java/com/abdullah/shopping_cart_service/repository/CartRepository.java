package com.abdullah.shopping_cart_service.repository;

import com.abdullah.shopping_cart_service.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<ShoppingCart, Long> {
}
