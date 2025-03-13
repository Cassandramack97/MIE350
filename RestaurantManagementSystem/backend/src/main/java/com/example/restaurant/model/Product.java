package com.example.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDate;

/**
 * Represents an item in the restaurant's inventory.
 * This could be a raw ingredient (e.g. tomatoes) or 
 * any supply item (e.g. napkins) depending on business needs.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the product (e.g. "Tomatoes", "Cheese").
     */
    @Column(nullable = false)
    private String name;

    /**
     * Current quantity in stock (e.g. number of units).
     */
    private int quantity;

    /**
     * Price (per unit or per some consistent measure).
     */
    private double price;

    /**
     * When the product will expire (if applicable). 
     * Null if not perishable.
     */
    private LocalDate expiryDate;

    // --- Constructors ---
    public Product() {
        // Default constructor for JPA
    }

    public Product(String name, int quantity, double price, LocalDate expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    // No setter for id if you want to prevent manual ID assignment.
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
