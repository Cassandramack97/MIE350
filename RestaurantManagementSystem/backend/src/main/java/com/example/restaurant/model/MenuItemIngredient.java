package com.example.restaurant.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_item_ingredients")
public class MenuItemIngredient {

    @EmbeddedId
    private MenuItemIngredientKey id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "menu_item_id")
    @JsonBackReference
    private MenuItem menuItem;

    @ManyToOne
    @MapsId("ingredientCode")
    @JoinColumn(name = "ingredient_code")
    @JsonBackReference
    private Ingredient ingredient;

    private double quantity; // The quantity used
    private String unit;     // Optionally, measurement unit (grams, cups, ml, etc.)

    public MenuItemIngredient() {}

    public MenuItemIngredient(MenuItemIngredientKey key, MenuItem menuItem, Ingredient ingredient, double quantity, String unit) {
        this.id = key;
        this.menuItem = menuItem;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.unit = unit;
    }

    public MenuItemIngredientKey getId() {
        return id;
    }

    public void setId(MenuItemIngredientKey id) {
        this.id = id;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}

