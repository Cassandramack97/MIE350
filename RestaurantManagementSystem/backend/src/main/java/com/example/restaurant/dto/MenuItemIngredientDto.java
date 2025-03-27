package com.example.restaurant.dto;

public class MenuItemIngredientDto {
    private Long menuItemId;
    private String ingredientCode;
    private double quantity;
    private String unit;

    // Constructor
    public MenuItemIngredientDto(String ingredientCode, double quantity, String unit) {
        this.ingredientCode = ingredientCode;
        this.quantity = quantity;
        this.unit = unit;
    }

    // Getters
    public Long getMenuItemId() {
        return menuItemId;
    }

    public String getIngredientCode() {
        return ingredientCode;
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

    public void setIngredientCode(String ingredientCode) {
        this.ingredientCode = ingredientCode;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
