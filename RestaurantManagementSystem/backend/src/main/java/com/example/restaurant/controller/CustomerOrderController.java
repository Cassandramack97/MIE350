package com.example.restaurant.controller;

import java.util.List;
import com.example.restaurant.model.CustomerOrder;
import com.example.restaurant.repository.CustomerOrderRepository;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customerOrder")
@CrossOrigin(origins = "*")
public class CustomerOrderController {

    private final CustomerOrderRepository repository;


    private final MenuItemRepository menuItemRepository;

    public CustomerOrderController(CustomerOrderRepository repository, MenuItemRepository menuItemRepository) {
        this.repository = repository;
        this.menuItemRepository = menuItemRepository;
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

    @DeleteMapping("/{id}")
    public void deleteCustomerOrder(@PathVariable("id") Long customerOrderId) {
        repository.deleteById(customerOrderId);
    }
}
