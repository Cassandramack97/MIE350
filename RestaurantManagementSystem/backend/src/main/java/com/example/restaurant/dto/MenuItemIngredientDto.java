package com.example.restaurant.dto;

public class MenuItemIngredientDto {
    private Long menuItemId;
    private Long ingredientId;
    private double quantity;
    private String unit;

    // Constructor
    public MenuItemIngredientDto(Long menuItemId, Long ingredientId, double quantity, String unit) {
        this.menuItemId = menuItemId;
        this.ingredientId = ingredientId;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Getters
    public Long getMenuItemId() {
        return menuItemId;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public double getQuantity() {
        return quantity;
    }

    public String getUnit() {
        return unit;
    }

    // Setters
    public void setMenuItemId(Long menuItemId) {
        this.menuItemId = menuItemId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
