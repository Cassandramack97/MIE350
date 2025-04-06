package com.example.restaurant.controller;

import com.example.restaurant.dto.SupplierOrderDto;
import com.example.restaurant.model.Ingredient;
import com.example.restaurant.model.Supplier;
import com.example.restaurant.model.SupplierOrder;
import com.example.restaurant.repository.IngredientRepository;
import com.example.restaurant.repository.SupplierOrderRepository;
import com.example.restaurant.repository.SupplierRepository;
import com.example.restaurant.repository.ProductRepository;
import com.example.restaurant.service.SupplierOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/supplierOrder")
@CrossOrigin(origins = "*")
public class SupplierOrderController {

    private final SupplierOrderRepository orderRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final IngredientRepository ingredientRepository;
    private final SupplierOrderService supplierOrderService;

    @Autowired
    public SupplierOrderController(SupplierOrderRepository orderRepository,
                                   ProductRepository productRepository,
                                   SupplierRepository supplierRepository,
                                   IngredientRepository ingredientRepository,
                                   SupplierOrderService supplierOrderService) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.ingredientRepository = ingredientRepository;
        this.supplierOrderService = supplierOrderService;
    }

    @GetMapping
    public List<SupplierOrder> retrieveAllSupplierOrders() {
        return orderRepository.findAll();
    }

    @GetMapping("/{id}")
    public SupplierOrder retrieveSupplierOrder(@PathVariable("id") Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SupplierOrder not found with id: " + id));
    }

    @PostMapping
    public SupplierOrder createSupplierOrder(@RequestBody SupplierOrderDto dto) {
        Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + dto.getSupplierId()));
        Ingredient ingredient = ingredientRepository.findById(dto.getIngredientId())
                .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + dto.getIngredientId()));

        SupplierOrder order = new SupplierOrder();
        order.setSupplier(supplier);
        order.setIngredient(ingredient);
        order.setQuantity(dto.getQuantity());
        order.setStatus(dto.getStatus());
        order.setDatePlaced(dto.getDatePlaced());
        order.setDateDelivered(dto.getDateDelivered());
        order.setProductName(dto.getProductName());
        order.setPrice(dto.getPrice());
        order.setExpiryDate(dto.getExpiryDate());

        return orderRepository.save(order);
    }

    @PutMapping("{id}")
    public SupplierOrder updateSupplierOrder(@PathVariable("id") Long id, @RequestBody SupplierOrderDto dto) {
        return orderRepository.findById(id)
                .map(order -> {
                    Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                            .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + dto.getSupplierId()));
                    Ingredient ingredient = ingredientRepository.findById(dto.getIngredientId())
                            .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + dto.getIngredientId()));
                    order.setSupplier(supplier);
                    order.setIngredient(ingredient);
                    order.setQuantity(dto.getQuantity());
                    order.setStatus(dto.getStatus());
                    order.setDatePlaced(dto.getDatePlaced());
                    order.setDateDelivered(dto.getDateDelivered());
                    order.setProductName(dto.getProductName());
                    order.setPrice(dto.getPrice());
                    order.setExpiryDate(dto.getExpiryDate());
                    return orderRepository.save(order);
                })
                .orElseGet(() -> {
                    dto.setId(id);
                    Supplier supplier = supplierRepository.findById(dto.getSupplierId())
                            .orElseThrow(() -> new RuntimeException("Supplier not found with id: " + dto.getSupplierId()));
                    Ingredient ingredient = ingredientRepository.findById(dto.getIngredientId())
                            .orElseThrow(() -> new RuntimeException("Ingredient not found with id: " + dto.getIngredientId()));
                    SupplierOrder order = new SupplierOrder();
                    order.setSupplier(supplier);
                    order.setIngredient(ingredient);
                    order.setQuantity(dto.getQuantity());
                    order.setStatus(dto.getStatus());
                    order.setDatePlaced(dto.getDatePlaced());
                    order.setDateDelivered(dto.getDateDelivered());
                    order.setProductName(dto.getProductName());
                    order.setPrice(dto.getPrice());
                    order.setExpiryDate(dto.getExpiryDate());
                    return orderRepository.save(order);
                });
    }

    @DeleteMapping("{id}")
    public void deleteSupplierOrder(@PathVariable("id") Long id) {
        orderRepository.deleteById(id);
    }

    // New endpoint: Refresh all supplier orders.
    // Expects a date parameter (ISO format, e.g. "2025-03-01").
    @PostMapping("/refresh/{date}")
    public void refreshSupplierOrders(@PathVariable("date") String dateStr) {
        LocalDate refreshDate = LocalDate.parse(dateStr);
        supplierOrderService.refreshSupplierOrders(refreshDate);
    }
}
