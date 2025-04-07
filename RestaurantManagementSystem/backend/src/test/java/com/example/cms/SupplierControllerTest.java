package com.example.cms;

import com.example.restaurant.model.Ingredient;
import com.example.restaurant.model.Supplier;
import com.example.restaurant.repository.IngredientRepository;
import com.example.restaurant.repository.SupplierRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional; // <-- Import this
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.example.restaurant.RestaurantManagementApplication.class)
@AutoConfigureMockMvc
@Transactional   // keeps lazy‑loaded collections accessible
public class SupplierControllerTest extends BaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void cleanDB() {
        // Clear out the DB before each test
        supplierRepository.deleteAll();
        ingredientRepository.deleteAll();
    }

    /**
     * Helper method to create & save an Ingredient in the DB.
     */
    private Ingredient createTestIngredient(Long code) {
        Ingredient ing = new Ingredient();
        ing.setIngredientCode(code);
        ing.setName("Ingredient " + code);
        return ingredientRepository.save(ing);
    }

    // ----------------------------------------------------------------
    // 1) GET /api/suppliers  (retriveAllSuppliers)
    // ----------------------------------------------------------------
    @Test
    void testRetrieveAllSuppliers() throws Exception {
        // Create a supplier in DB
        Supplier s = new Supplier();
        s.setName("Test Supplier");
        s.setContactInfo("test@supplier.com");
        s.setIngredientList(new ArrayList<>());
        supplierRepository.save(s);

        mockMvc.perform(get("/api/suppliers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Supplier"));
    }

    // ----------------------------------------------------------------
    // 2) GET /api/suppliers/{id}  (retriveSupplier)
    // ----------------------------------------------------------------
    @Test
    void testRetrieveSupplierById() throws Exception {
        Supplier s = new Supplier();
        s.setName("Specific Supplier");
        s.setContactInfo("specific@sup.com");
        s.setIngredientList(new ArrayList<>());
        Supplier saved = supplierRepository.save(s);

        mockMvc.perform(get("/api/suppliers/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Specific Supplier"));
    }

    // ----------------------------------------------------------------
    // 3) GET /api/suppliers/searchName/{name} (searchName)
    // ----------------------------------------------------------------
    @Test
    void testSearchName() throws Exception {
        Supplier s = new Supplier();
        s.setName("Ace Foods");
        s.setContactInfo("ace@food.com");
        s.setIngredientList(new ArrayList<>());
        supplierRepository.save(s);

        // Searching for "Ace" should return "Ace Foods"
        mockMvc.perform(get("/api/suppliers/searchName/Ace"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ace Foods"));
    }

    // ----------------------------------------------------------------
    // 4) POST /api/suppliers  (createSupplier)
    // ----------------------------------------------------------------
    @Test
    void testCreateSupplier() throws Exception {
        Ingredient ing1 = new Ingredient();
        ing1.setIngredientCode(101L);
        ing1.setName("Tomato");
        ing1 = ingredientRepository.save(ing1);

        Ingredient ing2 = new Ingredient();
        ing2.setIngredientCode(102L);
        ing2.setName("Lettuce");
        ing2 = ingredientRepository.save(ing2);

        // Create JSON with ingredient list as minimal Ingredient objects
        String json = """
    {
        "name": "Fresh Supplier",
        "contactInfo": "fresh@sup.com",
        "minimumOrder": 50,
        "deliveryTime": 2,
        "description": "Delivers fresh produce.",
        "ingredientList": [
            {"ingredientCode": %d},
            {"ingredientCode": %d}
        ]
    }
    """.formatted(ing1.getIngredientCode(), ing2.getIngredientCode());

        mockMvc.perform(post("/api/suppliers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Fresh Supplier"));

        Supplier saved = supplierRepository.findAll().get(0);
        Assertions.assertEquals(2, saved.getIngredientList().size());
    }

    // ----------------------------------------------------------------
    // 5) PUT /api/suppliers/{id} (updateSupplier)
    // ----------------------------------------------------------------
    @Test
    void testUpdateSupplier() throws Exception {
        Supplier s = new Supplier();
        s.setName("Old Supplier");
        s.setContactInfo("old@supply.com");
        s.setMinimumOrder(30);
        s.setDeliveryTime(4);
        s.setDescription("Old desc");
        s.setIngredientList(new ArrayList<>());
        s = supplierRepository.save(s);

        Ingredient ing1 = new Ingredient();
        ing1.setIngredientCode(201L);
        ing1.setName("Salt");
        ing1 = ingredientRepository.save(ing1);

        Ingredient ing2 = new Ingredient();
        ing2.setIngredientCode(202L);
        ing2.setName("Pepper");
        ing2 = ingredientRepository.save(ing2);

        String json = """
    {
        "name": "Updated Supplier",
        "contactInfo": "new@supply.com",
        "minimumOrder": 75,
        "deliveryTime": 1,
        "description": "Updated desc",
        "ingredientList": [
            {"ingredientCode": %d},
            {"ingredientCode": %d}
        ]
    }
    """.formatted(ing1.getIngredientCode(), ing2.getIngredientCode());

        mockMvc.perform(put("/api/suppliers/" + s.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Supplier"))
                .andExpect(jsonPath("$.ingredientList.length()").value(2));
    }

    // ----------------------------------------------------------------
    // 6) DELETE /api/suppliers/{id} (deleteSupplier)
    // ----------------------------------------------------------------
    @Test
    void testDeleteSupplier() throws Exception {
        // Insert a supplier
        Supplier s = new Supplier();
        s.setName("Delete Me Supplier");
        s.setContactInfo("delete@me.com");
        s.setIngredientList(new ArrayList<>());
        s = supplierRepository.save(s);

        mockMvc.perform(delete("/api/suppliers/" + s.getId()))
                .andExpect(status().isOk());

        Optional<Supplier> deleted = supplierRepository.findById(s.getId());
        Assertions.assertTrue(deleted.isEmpty());
    }
}