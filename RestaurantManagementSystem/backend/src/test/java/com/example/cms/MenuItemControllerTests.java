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
        Ingredient ing = new Ingredient("ING001", "Potato");
        ingredientRepository.save(ing);

        MenuItemIngredientDto ingredientDto = new MenuItemIngredientDto("ING001", 2.0, "pcs");

        MenuItemDto dto = new MenuItemDto();
        dto.setName("Fries");
        dto.setDescription("Fresh potatoes");
        dto.setPrice(3.5);
        dto.setIngredients(List.of(ingredientDto));

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(post("/api/menu-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fries"));

        assertEquals(1, menuItemRepository.count());
    }

    @Test
    void testUpdateMenuItem() throws Exception {
        Ingredient ing = new Ingredient("ING002", "Cheese");
        ingredientRepository.save(ing);

        MenuItem menuItem = new MenuItem("Pizza", "Cheesy goodness", 12.99);
        menuItem = menuItemRepository.save(menuItem);

        MenuItemIngredientDto ingredientDto = new MenuItemIngredientDto("ING002", 1.5, "cups");

        MenuItemDto dto = new MenuItemDto();
        dto.setName("Cheese Pizza");
        dto.setDescription("Even cheesier");
        dto.setPrice(14.5);
        dto.setIngredients(List.of(ingredientDto));

        String json = objectMapper.writeValueAsString(dto);

        mockMvc.perform(put("/api/menu-items/" + menuItem.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cheese Pizza"))
                .andExpect(jsonPath("$.price").value(14.5));
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