package com.example.restaurant.dto;

public class IngredientDto {
    private Long ingredientCode;
    private String name;
    private String measuredBy;

    // Constructor
    public IngredientDto(Long ingredientCode, String name, String measuredBy) {
        this.ingredientCode = ingredientCode;
        this.name = name;
        this.measuredBy = measuredBy;
    }

    // Getters
    public Long getIngredientCode() {
        return ingredientCode;
    }

    public String getName() {
        return name;
    }

    public String getMeasuredBy() {
        return measuredBy;
    }

    // Setters
    public void setIngredientCode(Long ingredientCode) {
        this.ingredientCode = ingredientCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMeasuredBy(String measuredBy) {
        this.measuredBy = measuredBy;
    }
}
