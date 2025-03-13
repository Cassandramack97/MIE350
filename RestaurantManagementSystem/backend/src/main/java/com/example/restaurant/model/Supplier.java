package com.example.restaurant.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

/**
 * Represents a supplier providing products to the restaurant.
 */
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Supplier or vendor name (e.g. "Fresh Farms Inc.")
     */
    @Column(nullable = false)
    private String name;

    /**
     * Contact info (could be phone, email, etc.).
     */
    private String contactInfo;

    // --- Constructors ---
    public Supplier() {
        // Default constructor for JPA
    }

    public Supplier(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }
}
