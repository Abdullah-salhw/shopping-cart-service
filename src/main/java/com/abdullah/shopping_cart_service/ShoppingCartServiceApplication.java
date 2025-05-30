package com.abdullah.shopping_cart_service;

import com.abdullah.shopping_cart_service.model.Product;
import com.abdullah.shopping_cart_service.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class ShoppingCartServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingCartServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner init(ProductRepository productRepo) {
		return args -> {
			if (productRepo.count() == 0) {
				productRepo.saveAll(List.of(
						new Product(null, "T-Shirt", "Blue Cotton Shirt", new BigDecimal("75.00"), new BigDecimal("0.15"), 100),
						new Product(null, "Orange Juice", "Fresh 1L bottle", new BigDecimal("12.00"), new BigDecimal("0.05"), 50),
						new Product(null, "Laptop", "Gaming Laptop", new BigDecimal("3500.00"), new BigDecimal("0.10"), 20),
						new Product(null, "Headphones", "Wireless noise-cancelling", new BigDecimal("299.00"), new BigDecimal("0.15"), 40),
						new Product(null, "Notebook", "A5 lined notebook", new BigDecimal("10.00"), new BigDecimal("0.00"), 200),
						new Product(null, "Water Bottle", "1L reusable plastic bottle", new BigDecimal("25.00"), new BigDecimal("0.05"), 150),
						new Product(null, "Smartphone", "Mid-range Android device", new BigDecimal("1200.00"), new BigDecimal("0.15"), 30),
						new Product(null, "Backpack", "Waterproof travel backpack", new BigDecimal("180.00"), new BigDecimal("0.10"), 60),
						new Product(null, "Desk Lamp", "LED reading lamp", new BigDecimal("95.00"), new BigDecimal("0.10"), 70),
						new Product(null, "Sneakers", "Running shoes", new BigDecimal("220.00"), new BigDecimal("0.15"), 45),
						new Product(null, "Chocolate Bar", "Dark chocolate 85%", new BigDecimal("7.00"), new BigDecimal("0.05"), 300),
						new Product(null, "Pen", "Black gel ink pen", new BigDecimal("3.00"), new BigDecimal("0.00"), 500)
				));
			}
		};
	}
}