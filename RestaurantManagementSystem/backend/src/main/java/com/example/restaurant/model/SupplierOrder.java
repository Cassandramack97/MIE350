package com.example.restaurant.model;

import org.springframework.lang.Nullable;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Represents an order of an igredient made from a supplier.
 */
@Entity
@Table(name = "SupplierOrder")
public class SupplierOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    //Supplier that received the order
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier; 

    //Number of items ordered
    @Column(name= "quantity", nullable = false)
    private Integer quantity;

    //Order status
    @Column(name = "status", nullable = true)
    private String status;

    //Ingredient in the order
    @ManyToOne
    @JoinColumn(name = "ingredient_code", nullable = false)
    private Ingredient ingredient;

    //Order placed date
    @Column(name = "DATE_PLACED")
    private LocalDate datePlaced;

    //Order delivery date (Expected, calculated based on the suppliers delivery time)
    @Column(name = "DATE_DELIVERED")
    private LocalDate dateDelivered;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "price")
    private Double price;

    @Column(name = "product_expiry_date")
    private LocalDate expiryDate;

    public SupplierOrder() {}

    public SupplierOrder(Long id, Supplier supplier, Integer quantity, String status, Ingredient ingredient, LocalDate datePlaced, LocalDate dateDelivered, String productName, Double price, LocalDate expiryDate) {
        this.id = id;
        this.supplier = supplier;
        this.quantity = quantity;
        this.status = status;
        this.ingredient = ingredient;
        this.datePlaced = datePlaced;
        this.productName = productName;
        this.price = price;
        this.expiryDate = expiryDate;
        if (supplier != null && datePlaced != null) {
            this.dateDelivered = datePlaced.plusDays(supplier.getDeliveryTime());
        }
    }
    // Getters
    public Long getId() {
        return id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public Ingredient getIngredient() { return ingredient; }

    public LocalDate getDatePlaced() {
        return datePlaced;
    }

    public LocalDate getDateDelivered() {
        return dateDelivered;
    }

    public Double getPrice() {return price;}

    public LocalDate getExpiryDate() {return expiryDate; }

    public String getProductName() {return productName; }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIngredient(Ingredient ingredient) {this.ingredient = ingredient; }

    public void setDatePlaced(LocalDate datePlaced) {
        this.datePlaced = datePlaced;
    }

    public void setDateDelivered(LocalDate dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public void setPrice(Double price) {this.price = price; }

    public void setExpiryDate(LocalDate expiryDate) {this.expiryDate = expiryDate; }

    public void setProductName(String productName) {this.productName = productName; }
}

