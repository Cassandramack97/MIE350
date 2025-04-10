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
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represents an ingredient item. Parent to product.
 */
@Entity
@Table(name = "ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientCode;

    /**
     * The name of the ingredient (e.g. "Tomatoes", "Cheese").
     */
    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy="ingredient")
    @JsonManagedReference("ingredient-menuItemIngredient")
    private List<MenuItemIngredient> menuItems = new ArrayList<>();

    // Refers to the different product entities that are of this ingredient type
    @OneToMany(mappedBy="ingredient")
    @JsonManagedReference("ingredient-products")
    private List<Product> products = new ArrayList<>();

    // Refers to the relationship with suppliers, i.e., what ingredients are supplied by which suppliers
    @ManyToMany(mappedBy = "ingredientList")
    private List<Supplier> suppliers = new ArrayList<>();

    // Refers to the ingredients in a specific SupplierOrder
    @OneToMany(mappedBy = "ingredient") // Refers to the 'ingredient' field in SupplierOrders
    private List<SupplierOrder> orders = new ArrayList<>();

    // --- Constructors ---
    public Ingredient() {
        // Default constructor for JPA
    }

    public Ingredient(Long ingredientCode, String name) {
        this.ingredientCode = ingredientCode;
        this.name = name;
    }

    // --- Getters & Setters ---

    public Long getIngredientCode() {
        return ingredientCode;
    }

    public void setIngredientCode(Long code) {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @JsonIgnore
    public List<Supplier> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<Supplier> suppliers) {
        this.suppliers = suppliers;
    }
}
