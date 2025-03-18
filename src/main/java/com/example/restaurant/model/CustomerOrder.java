package com.example.restaurant.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CustomerOrder")
public class CustomerOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "dish_id", nullable = false)
    private Dish dish;

    @Column(name = "DATE", nullable = false)
    private Date date;

    public CustomerOrder() {}

    public CustomerOrder(Long id, Dish dish, Date date) {
        this.id = id;
        this.dish = dish;
        this.date = date;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    // Cost Calculation (Price * Quantity for each product)
    public double calculateCost() {
        return dish.getDishProducts().stream()
                .mapToDouble(dishProduct -> dishProduct.getProduct().getPrice() * dishProduct.getQuantity())
                .sum();
    }
}