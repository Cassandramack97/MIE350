package com.example.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;


import java.util.ArrayList;
import java.util.List;

/**
 * Represents a menu item (e.g., "Cheeseburger", "Veggie Pizza").
 */
@Entity
@Table(name = "menu_items")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Display name for the menu item (e.g. "Cheeseburger").
     */
    @Column(nullable = false)
    private String name;

    /**
     * Additional details or description (e.g. ingredients).
     */
    private String description;

    private Double price;

    /**
     * Ingredients list
     */
    @OneToMany(mappedBy="menuItem")
    @JsonManagedReference("menuItem-menuItemIngredient")
    private List<MenuItemIngredient> ingredients = new ArrayList<>();

    // --- Constructors ---
    public MenuItem() {
        // Default constructor
    }

    public MenuItem(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
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

    public Double getPrice() {return price;}

    public void setPrice(Double price) {this.price = price;}

    public List<MenuItemIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<MenuItemIngredient> ingredients) {
        this.ingredients = ingredients;
    }
}