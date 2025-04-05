package com.example.restaurant.controller;

import com.example.restaurant.model.Ingredient;
import com.example.restaurant.model.Product;
import com.example.restaurant.repository.IngredientRepository;
import com.example.restaurant.repository.ProductRepository;
import com.example.restaurant.service.InventoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests for the InventoryController endpoints,
 * with no LocalDate usage in testCreateProduct / testUpdateProduct.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Optional if you have a separate test profile
class InventoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private InventoryService inventoryService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
        ingredientRepository.deleteAll();
    }

    /**
     * Helper method to create & save a test Ingredient.
     */
    private Ingredient createTestIngredient(String code) {
        Ingredient ing = new Ingredient();
        ing.setIngredientCode(code);
        ing.setName("Test Ingredient " + code);
        return ingredientRepository.save(ing);
    }

    // -----------------------------------------
    // 1) GET /api/inventory
    // -----------------------------------------
    @Test
    void testGetAllProducts() throws Exception {
        Ingredient ing = createTestIngredient("ING_A");

        Product product = new Product();
        product.setName("Test Product");
        product.setQuantity(10);
        product.setPrice(9.99);
        product.setIngredient(ing);
        product.setExpiryDate(LocalDate.now().plusDays(5));
        productRepository.save(product);

        mockMvc.perform(get("/api/inventory"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Test Product"))
                .andExpect(jsonPath("$[0].quantity").value(10));
    }

    // -----------------------------------------
    // 2) GET /api/inventory/{id}
    // -----------------------------------------
    @Test
    void testGetProductById() throws Exception {
        Ingredient ing = createTestIngredient("ING_B");

        Product product = new Product();
        product.setName("Unique Product");
        product.setQuantity(5);
        product.setPrice(12.50);
        product.setIngredient(ing);
        product.setExpiryDate(LocalDate.now().plusDays(10));
        Product savedProduct = productRepository.save(product);

        mockMvc.perform(get("/api/inventory/" + savedProduct.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Unique Product"))
                .andExpect(jsonPath("$.quantity").value(5));
    }

    // -----------------------------------------
    // 3) POST /api/inventory
    // -- (No LocalDate usage, so no Jackson date needed)
    // -----------------------------------------
    @Test
void testCreateProduct() throws Exception {
    Ingredient ing = createTestIngredient("ING_C");

    // Manually build a simple JSON payload
    String json = """
        {
            "name": "New Product",
            "quantity": 2,
            "price": 4.99,
            "ingredient": {
                "ingredientCode": "%s"
            }
        }
        """.formatted(ing.getIngredientCode());

    mockMvc.perform(post("/api/inventory")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(json))
            .andExpect(status().isOk());

    Assertions.assertEquals(1, productRepository.count());
    Product saved = productRepository.findAll().get(0);
    Assertions.assertEquals("New Product", saved.getName());
}
    // -----------------------------------------
    // 4) PUT /api/inventory/{id}
    // -- (No LocalDate usage in the request body)
    // -----------------------------------------
    @Test
void testUpdateProduct() throws Exception {
    Ingredient ingOld = createTestIngredient("ING_OLD");
    Product existing = new Product();
    existing.setName("OldName");
    existing.setQuantity(1);
    existing.setPrice(1.99);
    existing.setIngredient(ingOld);
    existing = productRepository.save(existing);

    Ingredient ingNew = createTestIngredient("ING_NEW");

    // JSON with only ingredientCode
    String updatedJson = """
        {
            "name": "UpdatedName",
            "quantity": 99,
            "price": 9.99,
            "ingredient": {
                "ingredientCode": "%s"
            }
        }
        """.formatted(ingNew.getIngredientCode());

    mockMvc.perform(put("/api/inventory/" + existing.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(updatedJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("UpdatedName"))
            .andExpect(jsonPath("$.quantity").value(99))
            .andExpect(jsonPath("$.price").value(9.99));

    Product changed = productRepository.findById(existing.getId()).orElseThrow();
    Assertions.assertEquals("UpdatedName", changed.getName());
    Assertions.assertEquals("ING_NEW", changed.getIngredient().getIngredientCode());
}

    // -----------------------------------------
    // 5) DELETE /api/inventory/{id}
    // -----------------------------------------
    @Test
    void testDeleteProduct() throws Exception {
        Ingredient ing = createTestIngredient("ING_D");

        Product p = new Product();
        p.setName("DeleteMe");
        p.setQuantity(5);
        p.setPrice(2.99);
        p.setExpiryDate(LocalDate.now().plusDays(2));
        p.setIngredient(ing);
        p = productRepository.save(p);

        mockMvc.perform(delete("/api/inventory/" + p.getId()))
                .andExpect(status().isOk());

        Optional<Product> deleted = productRepository.findById(p.getId());
        Assertions.assertTrue(deleted.isEmpty());
    }

    // -----------------------------------------
    // 6) DELETE /api/inventory/remove-expired-and-empty
    // -----------------------------------------
    @Test
    void testRemoveExpiredAndEmptyProducts() throws Exception {
        Ingredient ing = createTestIngredient("ING_E");

        // Expired
        Product expired = new Product();
        expired.setName("Expired");
        expired.setQuantity(10);
        expired.setPrice(1.0);
        expired.setExpiryDate(LocalDate.now().minusDays(1));
        expired.setIngredient(ing);
        productRepository.save(expired);

        // Empty
        Product empty = new Product();
        empty.setName("Empty");
        empty.setQuantity(0);
        empty.setPrice(5.0);
        empty.setExpiryDate(LocalDate.now().plusDays(10));
        empty.setIngredient(ing);
        productRepository.save(empty);

        // Valid
        Product valid = new Product();
        valid.setName("Valid");
        valid.setQuantity(5);
        valid.setPrice(10.99);
        valid.setExpiryDate(LocalDate.now().plusDays(5));
        valid.setIngredient(ing);
        productRepository.save(valid);

        mockMvc.perform(delete("/api/inventory/remove-expired-and-empty"))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully removed 2 expired or empty products from inventory."));

        Assertions.assertFalse(productRepository.existsById(expired.getId()));
        Assertions.assertFalse(productRepository.existsById(empty.getId()));
        Assertions.assertTrue(productRepository.existsById(valid.getId()));
    }
}
