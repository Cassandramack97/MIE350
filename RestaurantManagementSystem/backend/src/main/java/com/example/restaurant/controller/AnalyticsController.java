package com.example.restaurant.controller;

import com.example.restaurant.model.CustomerOrder;
import com.example.restaurant.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    public static class ForecastResult {
        private LocalDate forecastDate;
        private String menuItemName;
        private double forecastQuantity;

        public ForecastResult(LocalDate forecastDate, String menuItemName, double forecastQuantity) {
            this.forecastDate = forecastDate;
            this.menuItemName = menuItemName;
            this.forecastQuantity = forecastQuantity;
        }

        public LocalDate getForecastDate() {
            return forecastDate;
        }

        public String getMenuItemName() {
            return menuItemName;
        }

        public double getForecastQuantity() {
            return forecastQuantity;
        }
    }

    @GetMapping("/forecast-orders")
    public ResponseEntity<List<ForecastResult>> forecastOrders(
            @RequestParam Long menuItemId,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam int forecastDays,
            @RequestParam(defaultValue = "0.3") double alpha
    ) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);

        List<CustomerOrder> orders = customerOrderRepository.findAll().stream()
                .filter(order -> order.getMenuItem().getId().equals(menuItemId))
                .filter(order -> !order.getDate().isBefore(start) && !order.getDate().isAfter(end))
                .collect(Collectors.toList());

        String menuItemName = orders.isEmpty() ? "Unknown" : orders.get(0).getMenuItem().getName();

        // Group by date and sum quantity
        Map<LocalDate, Integer> dailyData = new TreeMap<>();
        for (CustomerOrder order : orders) {
            dailyData.merge(order.getDate(), order.getQuantity(), Integer::sum);
        }

        if (dailyData.isEmpty()) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        List<LocalDate> sortedDates = new ArrayList<>(dailyData.keySet());
        Collections.sort(sortedDates);

        // Compute initial EWMA from actuals
        double ewma = dailyData.get(sortedDates.get(0));
        double previousEwma = ewma;

        for (int i = 1; i < sortedDates.size(); i++) {
            double actual = dailyData.get(sortedDates.get(i));
            previousEwma = ewma;
            ewma = alpha * actual + (1 - alpha) * previousEwma;
        }

        // Estimate simple trend from historical data (optional, adds variation)
        double trend = (ewma - dailyData.get(sortedDates.get(0))) / sortedDates.size();

        LocalDate lastDate = sortedDates.get(sortedDates.size() - 1);
        List<ForecastResult> results = new ArrayList<>();

        // Forecast iteratively, updating forecast each time using previous forecast plus trend
        for (int i = 1; i <= forecastDays; i++) {
            lastDate = lastDate.plusDays(1);

            // Increment ewma by trend to produce varying forecasts
            ewma += trend;

            results.add(new ForecastResult(lastDate, menuItemName, ewma));
        }

        return ResponseEntity.ok(results);
    }
}
