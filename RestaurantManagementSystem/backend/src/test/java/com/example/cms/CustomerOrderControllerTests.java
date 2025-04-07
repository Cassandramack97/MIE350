package com.example.cms;

import com.example.restaurant.model.*;
import com.example.restaurant.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = com.example.restaurant.RestaurantManagementApplication.class)
@AutoConfigureMockMvc
public class CustomerOrderControllerTests extends BaseControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .findAndRegisterModules();

    @BeforeEach
    void setup() {
        customerOrderRepository.deleteAll();
        menuItemRepository.deleteAll();
    }

    @Test
    void testGetAllCustomerOrders() throws Exception {
        MenuItem menuItem = new MenuItem("Pasta", "Creamy Alfredo", 13.0);
        menuItem = menuItemRepository.save(menuItem);

        CustomerOrder order = new CustomerOrder();
        order.setMenuItem(menuItem);
        order.setDate(LocalDate.now());
        order.setQuantity(1);
        customerOrderRepository.save(order);

        mockMvc.perform(get("/api/customerOrder"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].menuItem.name").value("Pasta"));
    }

    @Test
    void testGetCustomerOrderById() throws Exception {
        MenuItem menuItem = new MenuItem("Burger", "Juicy", 10.0);
        menuItem = menuItemRepository.save(menuItem);

        CustomerOrder order = new CustomerOrder();
        order.setMenuItem(menuItem);
        order.setDate(LocalDate.now());
        order.setQuantity(2);
        order = customerOrderRepository.save(order);

        mockMvc.perform(get("/api/customerOrder/" + order.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantity").value(2));
    }

    @Test
    void testCreateCustomerOrder() throws Exception {
        MenuItem menuItem = new MenuItem("Wings", "Spicy and crispy", 8.5);
        menuItem = menuItemRepository.save(menuItem);

        String json = """
    {
        "menuItem": { "id": %d },
        "quantity": 3,
        "date": "%s"
    }
    """.formatted(menuItem.getId(), LocalDate.now());

        mockMvc.perform(post("/api/customerOrder")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.menuItem.name").value("Wings"))
                .andExpect(jsonPath("$.quantity").value(3));

        assertEquals(1, customerOrderRepository.count());
    }

    @Test
    void testUpdateCustomerOrder() throws Exception {
        MenuItem item1 = menuItemRepository.save(new MenuItem("Soup", "Warm and hearty", 6.0));
        MenuItem item2 = menuItemRepository.save(new MenuItem("Salad", "Crisp greens", 7.5));

        CustomerOrder order = new CustomerOrder();
        order.setMenuItem(item1);
        order.setDate(LocalDate.now().minusDays(1));
        order.setQuantity(1);
        order = customerOrderRepository.save(order);

        String json = """
    {
        "menuItem": { "id": %d },
        "quantity": 2,
        "date": "%s"
    }
    """.formatted(item2.getId(), LocalDate.now());

        mockMvc.perform(put("/api/customerOrder/" + order.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.menuItem.name").value("Salad"))
                .andExpect(jsonPath("$.quantity").value(2));
    }

    @Test
    void testDeleteCustomerOrder() throws Exception {
        MenuItem menuItem = new MenuItem("Sushi", "Rolls", 15.0);
        menuItem = menuItemRepository.save(menuItem);

        CustomerOrder order = new CustomerOrder();
        order.setMenuItem(menuItem);
        order.setDate(LocalDate.now());
        order.setQuantity(1);
        order = customerOrderRepository.save(order);

        mockMvc.perform(delete("/api/customerOrder/" + order.getId()))
                .andExpect(status().isOk());

        Optional<CustomerOrder> deleted = customerOrderRepository.findById(order.getId());
        assertTrue(deleted.isEmpty());
    }
}