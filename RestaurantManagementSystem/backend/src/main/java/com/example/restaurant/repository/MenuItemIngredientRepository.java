package com.example.restaurant.repository;

import com.example.restaurant.model.MenuItemIngredient;
import com.example.restaurant.model.MenuItemIngredientKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides CRUD operations for MenuItemIngredient entities.
 */
@Repository
public interface MenuItemIngredientRepository extends JpaRepository<MenuItemIngredient, MenuItemIngredientKey> {
    // Custom queries if needed
}
