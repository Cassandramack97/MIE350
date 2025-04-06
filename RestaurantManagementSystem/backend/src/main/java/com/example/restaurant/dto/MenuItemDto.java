package com.example.restaurant.dto;
import com.example.restaurant.dto.MenuItemIngredientDto;
import java.util.List;

public class MenuItemDto {
    private String name;
    private String description;
    private Double price;
    private List<MenuItemIngredientDto> ingredients;

    // Constructor
    public MenuItemDto() {}

    public MenuItemDto(String name, String description, Double price, List<MenuItemIngredientDto> ingredients) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public List<MenuItemIngredientDto> getIngredients() {return ingredients;}


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setIngredients(List<MenuItemIngredientDto> ingredients) {this.ingredients = ingredients;}
}
