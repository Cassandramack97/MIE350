package com.example.restaurant.model;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Represents an item in the restaurant's inventory.
 * This could be a raw ingredient (e.g. tomatoes) or 
 * any supply item (e.g. napkins) depending on business needs.
 */
@Entity
@Table(name = "products")
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

    @ManyToOne
    @JoinColumn(name = "ingredient_code", nullable = false)
    @JsonBackReference("ingredient-products")
    private Ingredient ingredient;

    // Refers to the products in a specific SupplierOrder
    @OneToMany(mappedBy = "product") // Refers to the 'product' field in SupplierOrders
    private List<SupplierOrder> orders = new ArrayList<>();

    /**
     * When the product will expire (if applicable).
     * Null if not perishable.
     */
    private LocalDate expiryDate;

    // --- Constructors ---
    public Product() {
        // Default constructor for JPA
    }

    public Product(String name, int quantity, double price, LocalDate expiryDate, Ingredient ingredient) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiryDate = expiryDate;
        this.ingredient = ingredient;
    }

    // --- Getters & Setters ---

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

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    // Updated to return a Long instead of a String.
    public Long getIngredientCode() {
        return ingredient != null ? ingredient.getIngredientCode() : null;
    }
}
