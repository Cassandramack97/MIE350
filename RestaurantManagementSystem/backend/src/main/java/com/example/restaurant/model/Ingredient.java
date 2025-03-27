package com.example.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonManagedReference;


/**
 * Represents an ingredient item. Parent to product.
 */
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    private String ingredientCode;

    /**
     * The name of the ingredient (e.g. "Tomatoes", "Cheese").
     */
    @Column(nullable = false)
    private String name;


    @OneToMany(mappedBy="ingredient")
    @JsonManagedReference
    private List<MenuItemIngredient> menuItems = new ArrayList<>();

    // --- Constructors ---
    public Ingredient() {
        // Default constructor for JPA
    }

    public Ingredient(String ingredientCode, String name) {
        this.ingredientCode = ingredientCode;
        this.name = name;
    }

    // --- Getters & Setters ---

    public String getIngredientCode() {
        return ingredientCode;
    }

    public void setIngredientCode(String code) {
        this.ingredientCode = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MenuItemIngredient> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemIngredient> menuItems) {
        this.menuItems = menuItems;
    }
}