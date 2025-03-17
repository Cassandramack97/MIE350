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
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    //Order placed date
    @Column(name = "DATE_PLACED")
    private LocalDate datePlaced;

    //Order delivery date (Expected, calculated based on the suppliers delivery time)
    @Column(name = "DATE_DELIVERED")
    private LocalDate dateDelivered;

    public SupplierOrder() {}

    public SupplierOrder(Long id, Supplier supplier, Integer quantity, String status, Product product, LocalDate datePlaced, LocalDate dateDelivered) {
        this.id = id;
        this.supplier = supplier;
        this.quantity = quantity;
        this.status = status;
        this.product = product;
        this.datePlaced = datePlaced;
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

    public Product getProduct() {
        return product;
    }

    public LocalDate getDatePlaced() {
        return datePlaced;
    }

    public LocalDate getDateDelivered() {
        return dateDelivered;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setDatePlaced(LocalDate datePlaced) {
        this.datePlaced = datePlaced;
    }

    public void setDateDelivered(LocalDate dateDelivered) {
        this.dateDelivered = dateDelivered;
    }
}

