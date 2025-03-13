package com.example.restaurant.controller;

import com.example.restaurant.model.Product;
import com.example.restaurant.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A REST controller for managing inventory (Product entities).
 */
@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * GET /api/inventory
     * Returns all products in inventory.
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return inventoryService.getAllProducts();
    }

    /**
     * GET /api/inventory/{id}
     * Returns a specific product by ID, or 404 if not found.
     */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {
        Product product = inventoryService.getProductById(id);
        if (product == null) {
            // In a real-world app, you'd throw an exception or return a ResponseEntity
            throw new RuntimeException("Product not found with id: " + id);
        }
        return product;
    }

    /**
     * POST /api/inventory
     * Creates a new product from JSON in the request body.
     */
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return inventoryService.createProduct(product);
    }

    /**
     * PUT /api/inventory/{id}
     * Updates an existing product's fields by ID.
     */
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updated) {
        Product product = inventoryService.updateProduct(id, updated);
        if (product == null) {
            throw new RuntimeException("Failed to update. Product not found with id: " + id);
        }
        return product;
    }

    /**
     * DELETE /api/inventory/{id}
     * Deletes a product by ID.
     */
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        boolean deleted = inventoryService.deleteProduct(id);
        if (!deleted) {
            throw new RuntimeException("Could not delete. Product not found with id: " + id);
        }
    }
}
