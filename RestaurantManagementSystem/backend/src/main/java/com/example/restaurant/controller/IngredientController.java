package com.example.restaurant.controller;

import com.example.restaurant.model.Ingredient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.restaurant.repository.IngredientRepository;

/**
 * A REST controller for managing inventory (Product entities).
 */
@RestController
@RequestMapping("/api/ingredient")
@CrossOrigin(origins = "*")
public class IngredientController {

    private final IngredientRepository repository;

    public IngredientController(IngredientRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Ingredient> getAllIngredients() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Ingredient retriveIngredient(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + id));
    }

    @PostMapping
    public Ingredient createIngredient(@RequestBody Ingredient newIngredient) {
        return repository.save(newIngredient);
    }

    @PutMapping("{id}")
    public Ingredient updateIngredient(@RequestBody Ingredient newIngredient, @PathVariable("id") Long ingredientId) {
        return repository.findById(ingredientId)
                .map(ingredient -> {
                    ingredient.setIngredientCode(newIngredient.getIngredientCode());
                    ingredient.setName(newIngredient.getName());
                    return repository.save(ingredient);
                })
                .orElseGet(() -> {
                    newIngredient.setIngredientCode(ingredientId);
                    return repository.save(newIngredient);
                });
    }

    @DeleteMapping("{id}")
    public void deleIngredient(@PathVariable("id") Long ingredientId) {
        repository.deleteById(ingredientId);
    }
}
