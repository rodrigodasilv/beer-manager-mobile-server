package com.beluga.beer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "b_beer")
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "alcohol_content")
    private double alcoholContent;

    @Column(name = "type")

    private String type;

    @Column(name = "origin")
    private String origin;

    @Column(name = "brewery")
    private String brewery;

    @Column(name = "price")
    private double price;

    @Column(name = "available")
    private boolean available;

    public Beer(Long id, String name, String description, double alcoholContent, String type, String origin, String brewery, double price, boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.alcoholContent = alcoholContent;
        this.type = type;
        this.origin = origin;
        this.brewery = brewery;
        this.price = price;
        this.available = available;
    }

    public Beer(String name, String description, double alcoholContent, String type, String origin,
                String brewery, double price, boolean available) {
        this.name = name;
        this.description = description;
        this.alcoholContent = alcoholContent;
        this.type = type;
        this.origin = origin;
        this.brewery = brewery;
        this.price = price;
        this.available = available;
    }

    public Beer(){}

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAlcoholContent() {
        return alcoholContent;
    }

    public void setAlcoholContent(double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
