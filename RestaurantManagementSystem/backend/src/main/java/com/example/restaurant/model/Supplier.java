package com.example.restaurant.model;

import org.springframework.lang.Nullable;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a supplier providing products to the restaurant.
 */
@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Supplier or vendor name (e.g. "Fresh Farms Inc.")
    @Column(nullable = false)
    private String name;

    //Available contact info (could be phone, email, etc.)
    @Column(name = "contact_info", nullable = true)
    private String contactInfo;

    //List of ingredients provided by the supplier
    @ManyToMany
    @JoinTable(name = "suppliers_ingredients",
            joinColumns = @JoinColumn(name = "supplier_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_code"))
    private List<Product> ingredientList;

    //Minimum order size
    @Column(name = "MINIMUM_ORDER")
    private Integer minimumOrder;

    //Expected delivery time for an order from this supplier
    @Column(name = "DELIVERY_TIME")
    private Integer deliveryTime;

    //Supplier description (fishmonger, butcher, etc.)
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    public Supplier(Long id, String name, String contactInfo, List<Product> ingredientList) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.ingredientList = ingredientList;
    }
    
    public Supplier() {}
    
    public Supplier(Long id, String name, String contactInfo, List <Product> ingredientList, Integer minimumOrder, Integer deliveryTime, String description) {
        this.id = id;
        this.name = name; 
        this.contactInfo = contactInfo; 
        this.ingredientList = ingredientList;
        this.minimumOrder = minimumOrder;
        this.deliveryTime = deliveryTime;
        this.description = description;
    }
    //Getters 

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public List<Product> getIngredientList() {
        return ingredientList;
    }

    public Integer getMinimumOrder() {
        return minimumOrder;
    }

    public Integer getDeliveryTime() {
        return deliveryTime;
    }

    public String getDescription() {
        return description;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public void setIngredientList(List<Product> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void setMinimumOrder(Integer minimumOrder) {
        this.minimumOrder = minimumOrder;
    }

    public void setDeliveryTime(Integer deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
