package com.example.restaurant.repository;

import com.example.restaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.restaurant.model.Ingredient;

import java.util.List;
/**
 * Provides CRUD operations for Product entities.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByIngredientOrderByExpiryDateAsc(Ingredient ingredient);
}
