package com.example.restaurant.repository;

import com.example.restaurant.model.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Provides CRUD operations for CustomerOrder entities.
 */
@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {
    // Add custom query methods if needed
    @Query(value = "select * from customer_order c " +
            "where c.date = :date", nativeQuery = true)
    List<CustomerOrder> findByDate(@Param("date") String date);
}
