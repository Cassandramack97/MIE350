package com.example.restaurant.controller;

import java.util.List;
import com.example.restaurant.model.CustomerOrder;
import com.example.restaurant.repository.CustomerOrderRepository;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.restaurant.service.InventoryService;

@RestController
@RequestMapping("/api/customerOrder")
@CrossOrigin(origins = "*")
public class CustomerOrderController {

    private final CustomerOrderRepository repository;

    private final MenuItemRepository menuItemRepository;

    private final InventoryService inventoryService;

    public CustomerOrderController(CustomerOrderRepository repository, MenuItemRepository menuItemRepository, InventoryService inventoryService) {
        this.repository = repository;
        this.menuItemRepository = menuItemRepository;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<CustomerOrder> retrieveAllCustomerOrders() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public CustomerOrder retrieveCustomerOrder(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("CustomerOrder not found with id: " + id));
    }

    @PostMapping
    public CustomerOrder createCustomerOrder(@RequestBody CustomerOrder newCustomerOrder) {
        MenuItem menuItem = menuItemRepository.findById(newCustomerOrder.getMenuItem().getId())
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));
        newCustomerOrder.setMenuItem(menuItem);
        return repository.save(newCustomerOrder);
    }
    //Create a new order and delete the inventory related to it
    @PostMapping("/createAndDeduct")
    public ResponseEntity<?> createOrderAndDeductInventory(@RequestBody CustomerOrder newCustomerOrder) {
        try {
            MenuItem menuItem = menuItemRepository.findById(newCustomerOrder.getMenuItem().getId())
                    .orElseThrow(() -> new RuntimeException("MenuItem not found"));
            newCustomerOrder.setMenuItem(menuItem);
            // Save the order directly using repository
            CustomerOrder savedOrder = repository.save(newCustomerOrder);

            // Deduct inventory
            inventoryService.deductIngredientsFromInventory(savedOrder);

            return ResponseEntity.ok(savedOrder);
        } catch (IllegalArgumentException e) {
            // Handle insufficient inventory
            return ResponseEntity.badRequest().body("Insufficient inventory: " + e.getMessage());
        } catch (Exception e) {
            // Handle other potential errors
            return ResponseEntity.internalServerError().body("Error processing order: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public CustomerOrder updateCustomerOrder(@RequestBody CustomerOrder newCustomerOrder, @PathVariable("id") Long customerOrderId) {

        MenuItem menuItem = menuItemRepository.findById(newCustomerOrder.getMenuItem().getId())
                .orElseThrow(() -> new RuntimeException("MenuItem not found"));

        return repository.findById(customerOrderId)
                .map(customerOrder -> {
                    customerOrder.setMenuItem(menuItem);
                    customerOrder.setDate(newCustomerOrder.getDate());
                    customerOrder.setQuantity(newCustomerOrder.getQuantity());
                    return repository.save(customerOrder);
                })
                .orElseGet(() -> {
                    newCustomerOrder.setId(customerOrderId);
                    newCustomerOrder.setMenuItem(menuItem);
                    return repository.save(newCustomerOrder);
                });
    }

    @PutMapping("/deduct-inventory/{orderId}")
    public ResponseEntity<?> deductInventoryForOrder(@PathVariable Long orderId) {
        try {
            // Retrieve the order directly using repository
            CustomerOrder existingOrder = repository.findById(orderId)
                    .orElse(null);

            if (existingOrder == null) {
                return ResponseEntity.notFound().build();
            }

            // Deduct inventory
            inventoryService.deductIngredientsFromInventory(existingOrder);

            return ResponseEntity.ok("Inventory successfully deducted for order " + orderId);
        } catch (IllegalArgumentException e) {
            // Handle insufficient inventory
            return ResponseEntity.badRequest().body("Insufficient inventory: " + e.getMessage());
        } catch (Exception e) {
            // Handle other potential errors
            return ResponseEntity.internalServerError().body("Error deducting inventory: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerOrder(@PathVariable("id") Long customerOrderId) {
        repository.deleteById(customerOrderId);
    }
}
