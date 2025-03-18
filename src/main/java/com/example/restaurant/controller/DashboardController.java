package com.example.restaurant.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restaurant.model.Product;
import com.example.restaurant.service.InventoryService;

@RestController
@RequestMapping("/api/dashboard")
@CrossOrigin(origins = "http://backend:8081")
public class DashboardController {

    private final InventoryService inventoryService;

    public DashboardController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public Map<String, Object> getDashboardData() {
        List<Product> products = inventoryService.getAllProducts();

        // Filter out products that are out of stock
        List<Product> outOfStock = products.stream()
                .filter(product -> product.getQuantity() <= 0)
                .collect(Collectors.toList());

        // Filter products that are running low (greater than 0 and 10 or below)
        List<Product> runningLow = products.stream()
                .filter(product -> product.getQuantity() > 0 && product.getQuantity() <= 10)
                .collect(Collectors.toList());

        // Filter products that are expiring soon (within the next 7 days)
        LocalDate thresholdDate = LocalDate.now().plusDays(7);
        List<Product> expiringSoon = products.stream()
                .filter(product -> product.getExpiryDate() != null && product.getExpiryDate().isBefore(thresholdDate))
                .collect(Collectors.toList());

        // Determine expected busyness based on the count of running low items
        String busyness;
        if (runningLow.size() > 5) {
            busyness = "High";
        } else if (runningLow.size() > 0) {
            busyness = "Moderate";
        } else {
            busyness = "Low";
        }

        // Aggregate the dashboard data into a response map
        Map<String, Object> dashboardData = new HashMap<>();
        dashboardData.put("outOfStock", outOfStock);
        dashboardData.put("runningLow", runningLow);
        dashboardData.put("expiringSoon", expiringSoon);
        dashboardData.put("busyness", busyness);

        return dashboardData;
    }
}
