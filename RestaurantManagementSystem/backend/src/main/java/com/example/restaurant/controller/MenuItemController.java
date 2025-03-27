package com.example.restaurant.controller;

import com.example.restaurant.model.Product;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.repository.IngredientRepository;
import com.example.restaurant.repository.MenuItemIngredientRepository;
import com.example.restaurant.dto.*;
import com.example.restaurant.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/menu-items")
@CrossOrigin(origins = "*")
public class MenuItemController {

    private MenuItemRepository menuItemRepository;

    private IngredientRepository ingredientRepository;

    private MenuItemIngredientRepository menuItemIngredientRepository;

    public MenuItemController(MenuItemRepository menuItemRepository, IngredientRepository ingredientRepository, MenuItemIngredientRepository menuItemIngredientRepository ) {
        this.menuItemRepository = menuItemRepository;
        this.ingredientRepository = ingredientRepository;
        this.menuItemIngredientRepository = menuItemIngredientRepository;
    }

    @GetMapping
    public List<MenuItem> getAllMenuItems() {
        return menuItemRepository.findAll();
    }

    @GetMapping("/{id}")
    public MenuItem getMenuItemById(@PathVariable Long id) {
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));
    }

    @PutMapping("/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItemDto menuItemDto) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));

        // Update fields
        menuItem.setName(menuItemDto.getName());
        menuItem.setDescription(menuItemDto.getDescription());
        menuItem.setPrice(menuItemDto.getPrice());

        // Remove old associations
        menuItemIngredientRepository.deleteAll(menuItem.getIngredients());

        List<MenuItemIngredient> updatedAssociations = new ArrayList<>();

        for (MenuItemIngredientDto ingredientDto : menuItemDto.getIngredients()) {
            Ingredient ingredient = ingredientRepository.findById(ingredientDto.getIngredientCode())
                    .orElseThrow(() -> new RuntimeException("Ingredient not found: " + ingredientDto.getIngredientCode()));

            MenuItemIngredientKey key = new MenuItemIngredientKey(id, ingredient.getIngredientCode());

            MenuItemIngredient menuItemIngredient = new MenuItemIngredient();
            menuItemIngredient.setId(key);
            menuItemIngredient.setMenuItem(menuItem);
            menuItemIngredient.setIngredient(ingredient);
            menuItemIngredient.setQuantity(ingredientDto.getQuantity());
            menuItemIngredient.setUnit(ingredientDto.getUnit());

            updatedAssociations.add(menuItemIngredient);
        }

        menuItemIngredientRepository.saveAll(updatedAssociations);
        menuItem.setIngredients(updatedAssociations);

        return menuItemRepository.save(menuItem);
    }


    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItemDto menuItemDto) {

        // Step 1: Create and save the new MenuItem
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDto.getName());
        menuItem.setDescription(menuItemDto.getDescription());
        //menuItem.setPrice(menuItemDto.getPrice());
        menuItemRepository.save(menuItem); // saving first to get menuItemId

        // Step 2: Associate Ingredients with the MenuItem
        List<MenuItemIngredient> ingredientAssociations = new ArrayList<>();

        for (MenuItemIngredientDto ingredientDto : menuItemDto.getIngredients()) {
            Ingredient ingredient = ingredientRepository.findById(ingredientDto.getIngredientCode())
                    .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + ingredientDto.getIngredientCode()));

            MenuItemIngredient menuItemIngredient = new MenuItemIngredient();
            MenuItemIngredientKey key = new MenuItemIngredientKey(menuItem.getId(), ingredient.getIngredientCode());

            menuItemIngredient.setId(key);
            menuItemIngredient.setMenuItem(menuItem);
            menuItemIngredient.setIngredient(ingredient);
            menuItemIngredient.setQuantity(ingredientDto.getQuantity());
            menuItemIngredient.setUnit(ingredientDto.getUnit());

            ingredientAssociations.add(menuItemIngredient);
        }

        menuItemIngredientRepository.saveAll(ingredientAssociations);

        // update associations in MenuItem entity (optional but recommended)
        menuItem.setIngredients(ingredientAssociations);

        return menuItem;
    }

    @DeleteMapping("/{id}")
    public void deleteMenuItem(@PathVariable Long id) {
        MenuItem menuItem = menuItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found with id: " + id));

        // Remove ingredient associations first
        menuItemIngredientRepository.deleteAll(menuItem.getIngredients());

        // Then delete the menu item
        menuItemRepository.delete(menuItem);
    }

}
