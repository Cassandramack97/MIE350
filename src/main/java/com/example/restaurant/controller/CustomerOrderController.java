package com.example.restaurant.controller;

import java.util.List;
import com.example.restaurant.model.CustomerOrder;
import com.example.restaurant.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customerOrder")
@CrossOrigin(origins = "http://backend:8081")
public class CustomerOrderController {
    @Autowired
    private final CustomerOrderRepository repository;

    public CustomerOrderController(CustomerOrderRepository repository) {
        this.repository = repository;
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
        return repository.save(newCustomerOrder);
    }

    @PutMapping("/{id}")
    public CustomerOrder updateCustomerOrder(@RequestBody CustomerOrder newCustomerOrder, @PathVariable("id") Long customerOrderId) {
        return repository.findById(customerOrderId)
                .map(customerOrder -> {
                    customerOrder.setDish(newCustomerOrder.getDish());
                    customerOrder.setDate(newCustomerOrder.getDate());
                    return repository.save(customerOrder);
                })
                .orElseGet(() -> {
                    newCustomerOrder.setId(customerOrderId);
                    return repository.save(newCustomerOrder);
                });
    }

    @DeleteMapping("/{id}")
    public void deleteCustomerOrder(@PathVariable("id") Long customerOrderId) {
        repository.deleteById(customerOrderId);
    }
}
