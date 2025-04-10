package com.example.restaurant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.restaurant.repository.ProductRepository;
import com.example.restaurant.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

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

    @Transactional
    public void deductIngredientsFromInventory(CustomerOrder customerOrder) {
        // Get the menu item from the order
        MenuItem menuItem = customerOrder.getMenuItem();

        // Get the ingredients for this menu item
        List<MenuItemIngredient> menuItemIngredients = menuItem.getIngredients();

        // Process each ingredient
        for (MenuItemIngredient menuItemIngredient : menuItemIngredients) {
            Ingredient ingredient = menuItemIngredient.getIngredient();
            double requiredQuantity = menuItemIngredient.getQuantity() * customerOrder.getQuantity();

            // Get all products for this ingredient, sorted by expiry date
            List<Product> ingredientProducts = productRepository.findByIngredientOrderByExpiryDateAsc(ingredient);

            // Check if we have enough inventory
            double totalAvailableQuantity = ingredientProducts.stream()
                    .mapToDouble(Product::getQuantity)
                    .sum();

            if (totalAvailableQuantity < requiredQuantity) {
                throw new IllegalArgumentException("Insufficient inventory for ingredient: " + ingredient.getName());
            }

            // Deduct from products, starting with those closest to expiry
            for (Product product : ingredientProducts) {
                if (requiredQuantity <= 0) break;

                // Deduct from this product
                double availableInProduct = product.getQuantity();
                double quantityToDeduct = Math.min(availableInProduct, requiredQuantity);

                double newQuantity = availableInProduct - quantityToDeduct;
                newQuantity = Math.round(newQuantity * 100.0) / 100.0;

                product.setQuantity(newQuantity);
                productRepository.save(product);

                // Reduce required quantity
                requiredQuantity -= quantityToDeduct;
            }
        }
    }

    @Transactional
    public int removeExpiredAndEmptyProducts() {
        LocalDate today = LocalDate.now();

        // Find products that are expired or have zero quantity
        List<Product> productsToRemove = productRepository.findByExpiryDateBeforeOrQuantityEquals(today, 0);

        int count = productsToRemove.size();

        // Remove the products
        productRepository.deleteAll(productsToRemove);

        return count;
    }
}
