package com.abdullah.shopping_cart_service.model;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Abdullah_Alhawas_Cart_Item")
public class CartItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) private Product product;
    @ManyToOne(optional = false) private ShoppingCart cart;

    private Integer quantity;
    private BigDecimal priceSnapshot;


    public BigDecimal getTaxSnapshot() {
        return taxSnapshot;
    }

    public void setTaxSnapshot(BigDecimal taxSnapshot) {
        this.taxSnapshot = taxSnapshot;
    }

    public BigDecimal getPriceSnapshot() {
        return priceSnapshot;
    }

    public void setPriceSnapshot(BigDecimal priceSnapshot) {
        this.priceSnapshot = priceSnapshot;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void setCart(ShoppingCart cart) {
        this.cart = cart;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private BigDecimal taxSnapshot;
}
