package com.example.restaurant.repository;

import com.example.restaurant.model.SupplierOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Provides CRUD operations for SupplierOrder entities.
 */
@Repository
public interface SupplierOrderRepository extends JpaRepository<SupplierOrder, Long> {
    // Add custom query methods if needed
}
