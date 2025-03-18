package com.example.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.restaurant.model.Product;
import com.example.restaurant.repository.ProductRepository;

/**
 * A service class to manage Product (inventory) data.
 * Provides common CRUD methods and business logic as needed.
 */
@Service
public class InventoryService {

    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Returns all products in inventory.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a single product by ID, or null if not found.
     * Feel free to throw an exception if not found, but for demonstration we return Optional or null.
     */
    public Product getProductById(Long id) {
        Optional<Product> productOpt = productRepository.findById(id);
        return productOpt.orElse(null);
    }

    /**
     * Creates and saves a new Product.
     */
    public Product createProduct(Product product) {
        // Example validation or business checks can go here
        return productRepository.save(product);
    }

    /**
     * Updates an existing Product’s fields, if found.
     */
    public Product updateProduct(Long id, Product updated) {
        Product existing = getProductById(id);
        if (existing == null) {
            return null; // or throw an exception
        }
        existing.setName(updated.getName());
        existing.setQuantity(updated.getQuantity());
        existing.setPrice(updated.getPrice());
        existing.setExpiryDate(updated.getExpiryDate());
        return productRepository.save(existing);
    }

    /**
     * Deletes a product by ID.
     * Returns true if deleted successfully, false if product not found.
     */
    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }
}
