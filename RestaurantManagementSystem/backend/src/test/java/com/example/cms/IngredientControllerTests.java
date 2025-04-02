package com.example.cms;

import com.example.restaurant.model.Ingredient;
import com.example.restaurant.repository.IngredientRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.example.restaurant.RestaurantManagementApplication.class)
@AutoConfigureMockMvc
public class IngredientControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IngredientRepository ingredientRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        ingredientRepository.deleteAll();
    }

    @Test
    void testGetAllIngredients() throws Exception {
        Ingredient ing = new Ingredient("ING001", "Tomato");
        ingredientRepository.save(ing);

        mockMvc.perform(get("/api/ingredient"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].ingredientCode").value("ING001"))
                .andExpect(jsonPath("$[0].name").value("Tomato"));
    }

    @Test
    void testGetIngredientByCode() throws Exception {
        Ingredient ing = new Ingredient("ING002", "Onion");
        ing = ingredientRepository.save(ing);

        mockMvc.perform(get("/api/ingredient/" + ing.getIngredientCode()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredientCode").value("ING002"))
                .andExpect(jsonPath("$.name").value("Onion"));
    }

    @Test
    void testCreateIngredient() throws Exception {
        Ingredient ing = new Ingredient("ING003", "Garlic");

        String json = objectMapper.writeValueAsString(ing);

        mockMvc.perform(post("/api/ingredient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        assertEquals(1, ingredientRepository.count());
        Ingredient saved = ingredientRepository.findAll().get(0);
        assertEquals("Garlic", saved.getName());
        assertEquals("ING003", saved.getIngredientCode());
    }

    @Test
    void testUpdateIngredient() throws Exception {
        Ingredient ing = new Ingredient("ING004", "Pepper");
        ing = ingredientRepository.save(ing);

        ing.setName("Green Pepper");
        String json = objectMapper.writeValueAsString(ing);

        mockMvc.perform(put("/api/ingredient/" + ing.getIngredientCode())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Green Pepper"));
    }

    @Test
    void testDeleteIngredient() throws Exception {
        Ingredient ing = new Ingredient("ING005", "Cilantro");
        ing = ingredientRepository.save(ing);

        mockMvc.perform(delete("/api/ingredient/" + ing.getIngredientCode()))
                .andExpect(status().isOk());

        Optional<Ingredient> deleted = ingredientRepository.findById(ing.getIngredientCode());
        assertTrue(deleted.isEmpty());
    }
}