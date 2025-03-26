package com.example.restaurant.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MenuItemIngredientKey implements Serializable {

    @Column(name = "itemId")
    Long itemId;

    @Column(name = "ingredientId")
    Long productId;

    @Override
    public int hashCode() {
        String concatString = String.valueOf(itemId.hashCode()) + String.valueOf(productId.hashCode());
        return concatString.hashCode();
    }

    public MenuItemIngredientKey(){}

    public MenuItemIngredientKey(Long itemId, Long productId) {
        this.setItemId(itemId);
        this.setProductId(productId);
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
        return itemId.equals(other.itemId) && productId.equals(other.productId);
    }

    // --- Getters & Setters ---

    public Long getProductId() { return productId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId; }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

}