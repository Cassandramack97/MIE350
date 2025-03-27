package com.example.restaurant.controller;

import com.example.restaurant.model.Product;
import com.example.restaurant.service.InventoryService;
import com.example.restaurant.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A REST controller for managing inventory (Product entities).
 */
@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*")
public class InventoryController {

    private final ProductRepository repository;

    public InventoryController(ProductRepository repository) {
        this.repository = repository;
    }

    /**
     * GET /api/inventory
     * Returns all products in inventory.
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    /**
     * GET /api/inventory/{id}
     * Returns a specific product by ID
     */
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id) {return repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));  }

    /**
     * POST /api/inventory
     * Creates a new product from JSON in the request body.
     */
    @PostMapping
    Product createProduct(@RequestBody Product newProduct) {return repository.save(newProduct);}

    /**
     * PUT /api/inventory/{id}
     * Updates an existing product's fields by ID.
     */
    @PutMapping("{id}")
    public Product createProduct(@RequestBody Product newProduct, @PathVariable("id") Long productId) {

        return repository.findById(productId)
                .map(product -> {
                    product.setName(newProduct.getName());
                    product.setQuantity(newProduct.getQuantity());
                    product.setPrice(newProduct.getPrice());
                    product.setExpiryDate(newProduct.getExpiryDate());
                    product.setIngredient(newProduct.getIngredient());
                    return repository.save(product);
                })
                .orElseGet(() -> {
                    newProduct.setId(productId);
                    return repository.save(newProduct);
                });
    }

    /**
     * DELETE /api/inventory/{id}
     * Deletes a product by ID.
     */
    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Long productId) { repository.deleteById(productId);}

}
