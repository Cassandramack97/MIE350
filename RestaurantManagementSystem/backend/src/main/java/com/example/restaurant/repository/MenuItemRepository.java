package com.example.restaurant.repository;

import com.example.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Provides CRUD operations for MenuItem entities.
 */
@Repository
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    // Custom queries if needed
}
