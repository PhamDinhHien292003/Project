package com.example.vgc_project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity(name = "foods")
public class Foods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_food;

    @Column(name = "name_food")
    private String name_food;
    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public Long getId_food() {
        return id_food;
    }

    public void setId_food(Long id_food) {
        this.id_food = id_food;
    }

    public String getName_food() {
        return name_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
// Getters and Setters
}