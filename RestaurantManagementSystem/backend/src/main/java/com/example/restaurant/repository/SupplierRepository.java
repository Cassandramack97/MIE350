package com.example.restaurant.repository;

import com.example.restaurant.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Provides CRUD operations for Supplier entities.
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    // Add custom query methods if needed
    @Query(value = "select * from suppliers s " +
            "where lower(s.name) like lower(concat('%', :name, '%'))",
            nativeQuery = true)
    List<Supplier> searchName(@Param("name") String name);
}
