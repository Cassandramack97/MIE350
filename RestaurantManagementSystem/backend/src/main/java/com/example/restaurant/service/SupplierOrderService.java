package com.example.restaurant.service;

import com.example.restaurant.model.Product;
import com.example.restaurant.model.SupplierOrder;
import com.example.restaurant.repository.ProductRepository;
import com.example.restaurant.repository.SupplierOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class SupplierOrderService {

    private final ProductRepository productRepository;
    private final SupplierOrderRepository supplierOrderRepository;

    @Autowired
    public SupplierOrderService(ProductRepository productRepository,
                                SupplierOrderRepository supplierOrderRepository) {
        this.productRepository = productRepository;
        this.supplierOrderRepository = supplierOrderRepository;
    }

    /**
     * Creates a new Product in inventory based on the given SupplierOrder.
     * Uses the order's productName, quantity, computed unit price (order price divided by quantity),
     * expiryDate, and ingredient.
     *
     * @param order the supplier order from which to create the product.
     * @return the newly created Product.
     */
    @Transactional
    public Product createProductFromOrder(SupplierOrder order) {
        Product product = new Product();
        product.setName(order.getProductName());
        product.setQuantity(order.getQuantity());
        // Compute unit price as order price divided by quantity.
        double unitPrice = (order.getQuantity() != null && order.getQuantity() != 0 && order.getPrice() != null)
                ? order.getPrice() / order.getQuantity() : 0.0;
        product.setPrice(unitPrice);
        product.setExpiryDate(order.getExpiryDate());
        product.setIngredient(order.getIngredient());
        return productRepository.save(product);
    }

    /**
     * Refreshes all supplier orders by the given refreshDate.
     * For each order whose delivery date is before the refresh date and not already marked as "Delivered",
     * updates the order's status to "Delivered" and creates a corresponding Product in inventory.
     *
     * @param refreshDate the date used as a threshold for refreshing orders.
     */
    @Transactional
    public void refreshSupplierOrders(LocalDate refreshDate) {
        List<SupplierOrder> orders = supplierOrderRepository.findAll();
        for (SupplierOrder order : orders) {
            if (order.getDateDelivered() != null
                    && !order.getDateDelivered().isAfter(refreshDate)
                    && (order.getStatus() == null || !order.getStatus().equalsIgnoreCase("Delivered"))) {
                order.setStatus("Delivered");
                supplierOrderRepository.save(order);
                createProductFromOrder(order);
            }
        }
    }
}
