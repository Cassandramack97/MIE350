package com.example.restaurant.repository;

import com.example.restaurant.model.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides CRUD operations for Ingredient entities.
 */
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    // Custom queries if needed
}
