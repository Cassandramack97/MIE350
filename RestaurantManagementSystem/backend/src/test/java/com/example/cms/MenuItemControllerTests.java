package com.example.cms;

import com.example.restaurant.model.*;
import com.example.restaurant.repository.*;
import com.example.restaurant.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.example.restaurant.RestaurantManagementApplication.class)
@AutoConfigureMockMvc
public class MenuItemControllerTests extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private MenuItemIngredientRepository menuItemIngredientRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        menuItemIngredientRepository.deleteAll();
        menuItemRepository.deleteAll();
        ingredientRepository.deleteAll();
    }

    @Test
    void testGetAllMenuItems() throws Exception {
        MenuItem menuItem = new MenuItem("Burger", "Beef patty", 9.99);
        menuItemRepository.save(menuItem);

        mockMvc.perform(get("/api/menu-items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Burger"));
    }

    @Test
    void testGetMenuItemById() throws Exception {
        MenuItem menuItem = new MenuItem("Fries", "Crispy fries", 4.99);
        menuItem = menuItemRepository.save(menuItem);

        mockMvc.perform(get("/api/menu-items/" + menuItem.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fries"));
    }

    @Test
    void testCreateMenuItem() throws Exception {
        Ingredient ing = new Ingredient();
        ing.setIngredientCode(1L);
        ing.setName("Potato");
        ing = ingredientRepository.save(ing);

        MenuItemIngredientDto ingredientDto = new MenuItemIngredientDto(
                ing.getIngredientCode(), 2.0, "pcs"
        );

        MenuItemDto dto = new MenuItemDto();
        dto.setName("Fries");
        dto.setDescription("Crispy fries");
        dto.setPrice(3.5);
        dto.setIngredients(List.of(ingredientDto));

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/menu-items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fries"));
    }

    @Test
    void testUpdateMenuItem() throws Exception {
        Ingredient ing = new Ingredient();
        ing.setIngredientCode(2L);
        ing.setName("Mozzarella");
        ing = ingredientRepository.save(ing);

        MenuItem menuItem = new MenuItem("Pizza", "Cheesy slice", 11.0);
        menuItem = menuItemRepository.save(menuItem);

        MenuItemIngredientDto dtoIng = new MenuItemIngredientDto(
                ing.getIngredientCode(), 1.5, "cups"
        );

        MenuItemDto updateDto = new MenuItemDto();
        updateDto.setName("Updated Pizza");
        updateDto.setDescription("Extra cheese");
        updateDto.setPrice(13.0);
        updateDto.setIngredients(List.of(dtoIng));

        String json = objectMapper.writeValueAsString(updateDto);

        mockMvc.perform(put("/api/menu-items/" + menuItem.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Pizza"))
                .andExpect(jsonPath("$.price").value(13.0));
    }

    @Test
    void testDeleteMenuItem() throws Exception {
        MenuItem menuItem = new MenuItem("Salad", "Fresh greens", 5.99);
        menuItem = menuItemRepository.save(menuItem);

        mockMvc.perform(delete("/api/menu-items/" + menuItem.getId()))
                .andExpect(status().isOk());

        Optional<MenuItem> deleted = menuItemRepository.findById(menuItem.getId());
        assertTrue(deleted.isEmpty());
    }
}