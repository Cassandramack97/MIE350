package com.example.restaurant.controller;

import com.example.restaurant.model.Product;
import com.example.restaurant.service.InventoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private MenuItemIngredientRepository menuItemIngredientRepository;

    @PostMapping
    public MenuItem createMenuItem(@RequestBody MenuItemDto menuItemDto) {

        // Step 1: Create and save the new MenuItem
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDto.getName());
        menuItem.setDescription(menuItemDto.getDescription());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItemRepository.save(menuItem); // saving first to get menuItemId

        // Step 2: Associate Ingredients with the MenuItem
        List<MenuItemIngredient> ingredientAssociations = new ArrayList<>();

        for (MenuItemIngredientDto ingredientDto : menuItemDto.getIngredients()) {
            Ingredient ingredient = ingredientRepository.findById(ingredientDto.getIngredientId())
                    .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + ingredientDto.getIngredientId()));

            MenuItemIngredient menuItemIngredient = new MenuItemIngredient();
            MenuItemIngredientKey key = new MenuItemIngredientKey(menuItem.getMenuItemId(), ingredient.getIngredientId());

            menuItemIngredient.setId(key);
            menuItemIngredient.setMenuItem(menuItem);
            menuItemIngredient.setIngredient(ingredient);
            menuItemIngredient.setQuantity(ingredientDto.getQuantity());
            menuItemIngredient.setUnit(ingredientDto.getUnit());

            ingredientAssociations.add(menuItemIngredient);
        }

        menuItemIngredientRepository.saveAll(ingredientAssociations);

        // update associations in MenuItem entity (optional but recommended)
        menuItem.setMenuItemIngredients(ingredientAssociations);

        return menuItem;
    }
}
