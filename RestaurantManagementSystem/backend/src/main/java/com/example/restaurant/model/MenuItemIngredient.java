package com.example.restaurant.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "menu_item_ingredients")
public class MenuItemIngredient {

    @EmbeddedId
    private MenuItemIngredientKey id;

    @ManyToOne
    @MapsId("menuItemId")
    @JoinColumn(name = "menuItemId")
    private MenuItem menuItem;

    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredientId")
    private Ingredient ingredient;

    private double quantity; // The quantity used
    private String unit;     // Optionally, measurement unit (grams, cups, ml, etc.)
}