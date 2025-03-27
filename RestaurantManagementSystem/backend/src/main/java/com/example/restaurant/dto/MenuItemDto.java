package com.example.restaurant.dto;
import com.example.restaurant.dto.MenuItemIngredientDto;
import java.util.List;

public class MenuItemDto {
    private Long id;
    private String itemName;
    private String description;
    private Double price;
    private List<MenuItemIngredientDto> ingredients;

    // Constructor
    public MenuItemDto() {}

    public MenuItemDto(Long id, String itemName, String description, Double price, List<MenuItemIngredientDto> ingredients) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public List<MenuItemIngredientDto> getIngredients() {return ingredients;}

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setIngredients(List<MenuItemIngredientDto> ingredients) {this.ingredients = ingredients;}
}
