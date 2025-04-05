package com.example.restaurant.controller;

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
import org.springframework.test.context.ActiveProfiles;
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

/**
 * Tests for the SupplierController endpoints.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional  // <-- Keeps session open so we can access lazy-loaded lists
@ActiveProfiles("test") 
class SupplierControllerTest {

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
    private Ingredient createTestIngredient(String code) {
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
        // 1) We must have actual Ingredient(s) in DB for the codes we reference
        Ingredient ing1 = createTestIngredient("ING1");
        Ingredient ing2 = createTestIngredient("ING2");

        // 2) Build a Supplier with "fake" ingredient references
        Supplier s = new Supplier();
        s.setName("Fresh Supplier");
        s.setContactInfo("fresh@sup.com");
        s.setMinimumOrder(50);
        s.setDeliveryTime(2);
        s.setDescription("Supplier description");

        // We'll just set the code in each ingredient for the JSON body
        Ingredient ref1 = new Ingredient();
        ref1.setIngredientCode("ING1");

        Ingredient ref2 = new Ingredient();
        ref2.setIngredientCode("ING2");

        List<Ingredient> ingList = new ArrayList<>();
        ingList.add(ref1);
        ingList.add(ref2);
        s.setIngredientList(ingList);

        // 3) Convert to JSON & POST
        String json = objectMapper.writeValueAsString(s);

        mockMvc.perform(post("/api/suppliers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());

        // 4) Verify
        Assertions.assertEquals(1, supplierRepository.count());
        Supplier saved = supplierRepository.findAll().get(0);
        Assertions.assertEquals("Fresh Supplier", saved.getName());
        // LazyInitializationException is avoided because of @Transactional
        Assertions.assertEquals(2, saved.getIngredientList().size());
    }

    // ----------------------------------------------------------------
    // 5) PUT /api/suppliers/{id} (updateSupplier)
    // ----------------------------------------------------------------
    @Test
    void testUpdateSupplier() throws Exception {
        // Create a supplier
        Supplier original = new Supplier();
        original.setName("Old Name");
        original.setContactInfo("old@contact.com");
        original.setMinimumOrder(100);
        original.setDeliveryTime(3);
        original.setDescription("Old Desc");
        original.setIngredientList(new ArrayList<>());
        original = supplierRepository.save(original);

        // Insert some ingredients for the update
        Ingredient ing1 = createTestIngredient("ING_ONE");
        Ingredient ing2 = createTestIngredient("ING_TWO");

        // Build the updated Supplier object
        Supplier updateBody = new Supplier();
        updateBody.setName("New Name");
        updateBody.setContactInfo("new@contact.com");
        updateBody.setMinimumOrder(200);
        updateBody.setDeliveryTime(5);
        updateBody.setDescription("New Desc");

        // We only need the ingredient codes in the JSON
        Ingredient ref1 = new Ingredient();
        ref1.setIngredientCode("ING_ONE");
        Ingredient ref2 = new Ingredient();
        ref2.setIngredientCode("ING_TWO");

        List<Ingredient> updatedIngList = new ArrayList<>();
        updatedIngList.add(ref1);
        updatedIngList.add(ref2);
        updateBody.setIngredientList(updatedIngList);

        String json = objectMapper.writeValueAsString(updateBody);

        // Perform PUT
        mockMvc.perform(put("/api/suppliers/" + original.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"))
                .andExpect(jsonPath("$.deliveryTime").value(5));

        // Confirm in DB
        Supplier changed = supplierRepository.findById(original.getId()).orElseThrow();
        Assertions.assertEquals("New Name", changed.getName());
        Assertions.assertEquals("new@contact.com", changed.getContactInfo());
        Assertions.assertEquals(200, changed.getMinimumOrder());
        Assertions.assertEquals(5, changed.getDeliveryTime());
        // Because of @Transactional, we can check ingredientList safely:
        Assertions.assertEquals(2, changed.getIngredientList().size());
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
