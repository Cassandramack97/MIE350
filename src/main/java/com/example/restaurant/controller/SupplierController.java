package com.example.restaurant.controller;

import java.util.List;
import com.example.restaurant.model.Supplier;
import com.example.restaurant.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/suppliers")
@CrossOrigin(origins = "http://backend:8081")
public class SupplierController {
    @Autowired
    private final SupplierRepository repository;

    public SupplierController(SupplierRepository repository) {this.repository = repository;}

    @GetMapping
    List<Supplier> retriveAllSuppliers() {return repository.findAll();}

    @GetMapping("/{id}")
    Supplier retriveSupplier(@PathVariable("id") Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + id));
    }

    //Finds a supplier given a name
    @GetMapping("/searchName/{name}")
    List<Supplier> searchName(@PathVariable("name") String name) {
        return repository.searchName(name);
    }

    @PostMapping
    Supplier createSupplier(@RequestBody Supplier newSupplier) {return repository.save(newSupplier);}

    @PutMapping("{id}")
    Supplier updateSupplier(@RequestBody Supplier newSupplier, @PathVariable("id") Long supplierId) {
        return repository.findById(supplierId)
                .map(supplier -> {
                    supplier.setName(newSupplier.getName());
                    supplier.setContactInfo(newSupplier.getContactInfo());
                    supplier.setIngredientList(newSupplier.getIngredientList());
                    supplier.setMinimumOrder(newSupplier.getMinimumOrder());
                    supplier.setDeliveryTime(newSupplier.getDeliveryTime());
                    supplier.setDescription(newSupplier.getDescription());
                    return repository.save(supplier);
                })
                .orElseGet(() -> {
                    newSupplier.setId(supplierId);
                    return repository.save(newSupplier);
                });
    }

    @DeleteMapping("{id}")
    void deleteSupplier(@PathVariable("id") Long supplierId) { repository.deleteById(supplierId);}

}
