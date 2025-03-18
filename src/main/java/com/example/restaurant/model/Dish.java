package com.example.restaurant.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Dish")
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "dish", cascade = CascadeType.ALL)
    private List<DishProduct> dishProducts;

    public Dish() {}

    public Dish(Long id, String name, List<DishProduct> dishProducts) {
        this.id = id;
        this.name = name;
        this.dishProducts = dishProducts;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DishProduct> getDishProducts() {
        return dishProducts;
    }

    public void setDishProducts(List<DishProduct> dishProducts) {
        this.dishProducts = dishProducts;
    }
}