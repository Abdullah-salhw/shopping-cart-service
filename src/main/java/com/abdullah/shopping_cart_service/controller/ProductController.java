package com.abdullah.shopping_cart_service.controller;

import com.abdullah.shopping_cart_service.model.Product;
import com.abdullah.shopping_cart_service.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository repo;

    public ProductController(ProductRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Product> getAll() {
        return repo.findAll();
    }

    @GetMapping("/search")
    public List<Product> search(@RequestParam("q") String keyword) {
        return repo.findByNameContainingIgnoreCase(keyword);
    }

    @GetMapping("/{id}")
    public Product getOne(@PathVariable Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
