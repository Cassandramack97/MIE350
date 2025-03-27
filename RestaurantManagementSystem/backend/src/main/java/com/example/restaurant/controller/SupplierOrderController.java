package com.example.restaurant.controller;

import java.util.List;
import com.example.restaurant.model.SupplierOrder;
import com.example.restaurant.repository.SupplierOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supplierOrder")
@CrossOrigin(origins = "*")
public class SupplierOrderController {
    @Autowired
    private final SupplierOrderRepository repository;

    public SupplierOrderController(SupplierOrderRepository repository) {this.repository = repository;}

    @GetMapping
    List<SupplierOrder> retriveAllSupplierOrder() {return repository.findAll();}

    @GetMapping("/{id}")
    SupplierOrder retriveSupplierOrder(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("SupplierOrder not found with id: " + id));
    }

    @PostMapping
    SupplierOrder createSupplierOrder(@RequestBody SupplierOrder newSupplierOrder) {return repository.save(newSupplierOrder);}

    @PutMapping("{id}")
    SupplierOrder updateSupplierOrder(@RequestBody SupplierOrder newSupplierOrder, @PathVariable("id") Long supplierOrderId) {
        return repository.findById(supplierOrderId)
                .map(supplierOrder -> {
                    supplierOrder.setSupplier(newSupplierOrder.getSupplier());
                    supplierOrder.setQuantity(newSupplierOrder.getQuantity());
                    supplierOrder.setStatus(newSupplierOrder.getStatus());
                    supplierOrder.setProduct(newSupplierOrder.getProduct());
                    supplierOrder.setDatePlaced(newSupplierOrder.getDatePlaced());
                    supplierOrder.setDateDelivered(newSupplierOrder.getDateDelivered());
                    return repository.save(supplierOrder);
                })
                .orElseGet(() -> {
                    newSupplierOrder.setId(supplierOrderId);
                    return repository.save(newSupplierOrder);
                });
    }

    @DeleteMapping("{id}")
    void deleteSupplierOrder(@PathVariable("id") Long supplierOrderId) { repository.deleteById(supplierOrderId);}

}
