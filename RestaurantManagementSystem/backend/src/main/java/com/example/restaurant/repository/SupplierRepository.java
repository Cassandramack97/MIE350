package com.example.restaurant.repository;

import com.example.restaurant.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides CRUD operations for Supplier entities.
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    // Add custom query methods if needed
}
