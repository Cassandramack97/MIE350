package com.example.restaurant.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CustomerOrder")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menuItemId", nullable = false)
    private MenuItem menuItem;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Column(name = "QUANTITY", nullable = false)
    private Integer quantity;

    public CustomerOrder() {}

    public CustomerOrder(Long id, MenuItem menuItem, LocalDate date, int Quantity) {
        this.id = id;
        this.menuItem = menuItem;
        this.date = date;
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) { this.date = date;
    }

    public Integer getQuantity() {return quantity;
    }

    public void setQuantity(Integer quantity) {this.quantity = quantity;
    }
}
