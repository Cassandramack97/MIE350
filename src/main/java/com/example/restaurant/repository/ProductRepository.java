package com.example.restaurant.repository;

import com.example.restaurant.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides CRUD operations for Product entities.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Additional custom query methods if necessary
    // e.g. List<Product> findByNameContainingIgnoreCase(String name);
}
