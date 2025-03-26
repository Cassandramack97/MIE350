package com.example.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

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
    private String itemName;

    /**
     * Additional details or description (e.g. ingredients).
     */
    private String description;

    /**
     * Ingredients list
     */
    @OneToMany(mappedBy="menuItem")
    private List<MenuItemIngredient> ingredients = new ArrayList<>();

    // --- Constructors ---
    public MenuItem() {
        // Default constructor
    }

    public MenuItem(String itemName, String description) {
        this.itemName = itemName;
        this.description = description;
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}