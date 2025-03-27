package com.example.restaurant.dto;

public class MenuItemDto {
    private Long id;
    private String itemName;
    private String description;

    // Constructor
    public MenuItemDto(Long id, String itemName, String description) {
        this.id = id;
        this.itemName = itemName;
        this.description = description;
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
}
