package com.example.cms;

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
@SpringBootTest(classes = com.example.restaurant.RestaurantManagementApplication.class)
@AutoConfigureMockMvc
class InventoryControllerTest extends BaseControllerTest {

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
    private Ingredient createTestIngredient(Long code) {
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
        Ingredient ing = createTestIngredient(101L);

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
        Ingredient ing = createTestIngredient(102L);

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
        Ingredient ing = new Ingredient();
        ing.setIngredientCode(105L);
        ing.setName("Tomato");
        ing = ingredientRepository.save(ing);

        String json = """
        {
            "name": "New Product",
            "quantity": 2,
            "price": 4.99,
            "expiryDate": "%s",
            "ingredient": {
                "ingredientCode": %d
            }
        }
    """.formatted(LocalDate.now().plusDays(5), ing.getIngredientCode());

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
        Ingredient oldIng = new Ingredient();
        oldIng.setIngredientCode(200L);
        oldIng.setName("Salt");
        oldIng = ingredientRepository.save(oldIng);

        Ingredient newIng = new Ingredient();
        newIng.setIngredientCode(201L);
        newIng.setName("Pepper");
        newIng = ingredientRepository.save(newIng);

        Product p = new Product();
        p.setName("To Update");
        p.setQuantity(5);
        p.setPrice(2.99);
        p.setExpiryDate(LocalDate.now().plusDays(10));
        p.setIngredient(oldIng);
        p = productRepository.save(p);

        String json = """
        {
            "name": "Updated Product",
            "quantity": 10,
            "price": 6.99,
            "expiryDate": "%s",
            "ingredient": {
                "ingredientCode": %d
            }
        }
    """.formatted(LocalDate.now().plusDays(20), newIng.getIngredientCode());

        mockMvc.perform(put("/api/inventory/" + p.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Product"));

        Product updated = productRepository.findById(p.getId()).orElseThrow();
        Assertions.assertEquals("Pepper", updated.getIngredient().getName());
    }

    // -----------------------------------------
    // 5) DELETE /api/inventory/{id}
    // -----------------------------------------
    @Test
    void testDeleteProduct() throws Exception {
        Ingredient ing = createTestIngredient(106L);

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
        Ingredient ing = createTestIngredient(107L);

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