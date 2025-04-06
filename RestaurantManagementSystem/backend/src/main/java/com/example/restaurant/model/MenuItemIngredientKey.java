package com.example.restaurant.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MenuItemIngredientKey implements Serializable {

    @Column(name = "menu_item_id")
    private Long itemId;

    @Column(name = "ingredient_code")
    private Long ingredientCode;

    public MenuItemIngredientKey() {}

    public MenuItemIngredientKey(Long itemId, Long ingredientCode) {
        this.itemId = itemId;
        this.ingredientCode = ingredientCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, ingredientCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItemIngredientKey)) return false;
        MenuItemIngredientKey that = (MenuItemIngredientKey) o;
        return Objects.equals(itemId, that.itemId) &&
                Objects.equals(ingredientCode, that.ingredientCode);
    }

    // --- Getters & Setters ---

    public Long getIngredientCode() {
        return ingredientCode;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setIngredientCode(Long ingredientCode) {
        this.ingredientCode = ingredientCode;
    }
}
