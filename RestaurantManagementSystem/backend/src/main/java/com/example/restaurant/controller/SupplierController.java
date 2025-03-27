package com.example.restaurant.controller;

import java.util.List;
import java.util.ArrayList;
import com.example.restaurant.model.Supplier;
import com.example.restaurant.model.Ingredient;
import com.example.restaurant.repository.SupplierRepository;
import com.example.restaurant.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "http://backend:8081")
public class SupplierController {
    @Autowired
    private final SupplierRepository repository;

    @Autowired
    private final IngredientRepository ingredientRepository;

    public SupplierController(SupplierRepository repository, IngredientRepository ingredientRepository) {
        this.repository = repository;
        this.ingredientRepository = ingredientRepository;

    }

    @GetMapping
    List<Supplier> retriveAllSuppliers() {return repository.findAll();}

    @GetMapping("/{id}")
    Supplier retriveSupplier(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    //Finds a supplier given a name
    @GetMapping("/searchName/{name}")
    List<Supplier> searchName(@PathVariable("name") String name) {
        return repository.searchName(name);
    }

    // POST Mapping to create a new supplier
    @PostMapping
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        // Retrieve the full ingredient objects by their codes
        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient tempIngredient : supplier.getIngredientList()) {
            String ingredientCode = tempIngredient.getIngredientCode();
            Ingredient ingredient = ingredientRepository.findById(ingredientCode)
                    .orElseThrow(() -> new RuntimeException("Ingredient not found: " + ingredientCode));
            ingredients.add(ingredient);
        }
        // Set the full ingredient objects to the supplier
        supplier.setIngredientList(ingredients);

        // Save and return the supplier
        return repository.save(supplier);
    }

    // PUT Mapping to update an existing supplier
    @PutMapping("/{id}")
    public Supplier updateSupplier(@PathVariable Long id, @RequestBody Supplier supplier) {
        // Find the existing supplier
        Supplier existingSupplier = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        // Retrieve the full ingredient objects by their codes
        List<Ingredient> ingredients = new ArrayList<>();
        for (Ingredient tempIngredient : supplier.getIngredientList()) {
            String ingredientCode = tempIngredient.getIngredientCode();
            Ingredient ingredient = ingredientRepository.findById(ingredientCode)
                    .orElseThrow(() -> new RuntimeException("Ingredient not found: " + ingredientCode));
            ingredients.add(ingredient);
        }

        // Set the full ingredient objects to the supplier
        existingSupplier.setIngredientList(ingredients);

        // Update other fields as necessary
        existingSupplier.setName(supplier.getName());
        existingSupplier.setContactInfo(supplier.getContactInfo());
        existingSupplier.setMinimumOrder(supplier.getMinimumOrder());
        existingSupplier.setDeliveryTime(supplier.getDeliveryTime());
        existingSupplier.setDescription(supplier.getDescription());

        // Save and return the updated supplier
        return repository.save(existingSupplier);
    }

    @DeleteMapping("{id}")
    void deleteSupplier(@PathVariable("id") Long supplierId) { repository.deleteById(supplierId);}

}
