package com.example.restaurant.dto;

import java.time.LocalDate;

public class SupplierOrderDto {

    private Long id;
    private Long supplierId;
    private Integer quantity;
    private String status;
    private Long ingredientId;
    private LocalDate datePlaced;
    private LocalDate dateDelivered;
    private String productName;
    private Double price;       // total order price
    private LocalDate expiryDate;

    public SupplierOrderDto() {}

    public SupplierOrderDto(Long id, Long supplierId, Integer quantity, String status,
                            Long ingredientId, LocalDate datePlaced, LocalDate dateDelivered, String productName,
                            Double price, LocalDate expiryDate) {
        this.id = id;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.status = status;
        this.ingredientId = ingredientId;
        this.datePlaced = datePlaced;
        this.dateDelivered = dateDelivered;
        this.productName = productName;
        this.price = price;
        this.expiryDate = expiryDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public Long getIngredientId() {
        return ingredientId;
    }

    public LocalDate getDatePlaced() {
        return datePlaced;
    }

    public LocalDate getDateDelivered() {
        return dateDelivered;
    }

    public String getProductName() {
        return productName;
    }

    public Double getPrice() {
        return price;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setDatePlaced(LocalDate datePlaced) {
        this.datePlaced = datePlaced;
    }

    public void setDateDelivered(LocalDate dateDelivered) {
        this.dateDelivered = dateDelivered;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }
}
