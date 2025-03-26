package com.example.restaurant.controller;

import com.example.restaurant.model.Ingredient;
import com.example.restaurant.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * A REST controller for managing inventory (Product entities).
 */
@RestController
@RequestMapping("/api/ingredient")
@CrossOrigin(origins = "*")
public class IngredientController {

    @Autowired
    private final IngredientRepository repository;

    public IngredientController(IngredientRepository repository) {this.repository = repository;}

    @GetMapping
    List<Ingredient> getAllIngredients() {
        return repository.findAll();    }

    @GetMapping("/{id}")
    Ingredient retriveIngredient(@PathVariable("id") String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    @PostMapping
    Ingredient createIngredient(@RequestBody Ingredient newIngredient) {return repository.save(newIngredient);}

    @PutMapping("{id}")
    Supplier updateSupplier(@RequestBody Ingredient newIngredient, @PathVariable("id") String ingredientId) {
        return repository.findById(ingredientId)
                .map(ingredient -> {
                    ingredient.setIngredientCode(newIngredient.getIngredientCode());
                    ingredient.setName(newIngredient.getName());
                    ingredient.setMeasuredBy(newIngredient.getMeasuredBy());
                    return repository.save(ingredient);
                })
                .orElseGet(() -> {
                    newIngredient.setId(ingredientId);
                    return repository.save(newIngredient);
                });
    }

    @DeleteMapping("{id}")
    void deleIngredient(@PathVariable("id") String ingredientId) { repository.deleteById(ingredientId);}

}
