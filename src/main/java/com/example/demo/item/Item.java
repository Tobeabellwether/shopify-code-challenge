package com.example.demo.item;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
public class Item {
    @Id
    @SequenceGenerator(
            name = "item_sequence",
            sequenceName = "item_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_sequence"
    )

    private Long id;
    private String type;
    private String brand;
    private String model;
    private LocalDate checkInDate;

    public Item() {
    }

    public Item(String type, String brand, String model, LocalDate checkInDate) {
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.checkInDate = checkInDate;
    }

    public Item(Long id, String type, String brand, String model, LocalDate checkInDate) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.checkInDate = checkInDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", checkInDate=" + checkInDate +
                '}';
    }
}
