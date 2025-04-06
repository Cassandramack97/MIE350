package com.example.restaurant.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MenuItemIngredientKey implements Serializable {

    @Column(name = "menu_item_id")
    Long itemId;

    @Column(name = "ingredient_code")
    String ingredientCode;

    @Override
    public int hashCode() {
        String concatString = String.valueOf(itemId.hashCode()) + String.valueOf(ingredientCode.hashCode());
        return concatString.hashCode();
    }

    public MenuItemIngredientKey(){}

    public MenuItemIngredientKey(Long itemId, String ingredientCode) {
        this.setItemId(itemId);
        this.setIngredientCode(ingredientCode);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof MenuItemIngredientKey)) {
            return false;
        }
        MenuItemIngredientKey other = (MenuItemIngredientKey) o;
        return itemId.equals(other.itemId) && ingredientCode.equals(other.ingredientCode);
    }

    // --- Getters & Setters ---

    public String getIngredientCode() { return ingredientCode;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId; }

    public void setIngredientCode(String ingredientCode) {
        this.ingredientCode = ingredientCode;
    }

}