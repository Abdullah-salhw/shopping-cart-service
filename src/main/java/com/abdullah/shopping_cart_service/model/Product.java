package com.abdullah.shopping_cart_service.model;
import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "Abdullah_Alhawas_Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Product() {
    }
    public Product(Long id, String name, String description, BigDecimal price, BigDecimal taxRate, Integer stockQty) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.taxRate = taxRate;
        this.stockQty = stockQty;
    }

    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal taxRate;
    private Integer stockQty;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public Integer getStockQty() {
        return stockQty;
    }

    public void setStockQty(Integer stockQty) {
        this.stockQty = stockQty;
    }
}



