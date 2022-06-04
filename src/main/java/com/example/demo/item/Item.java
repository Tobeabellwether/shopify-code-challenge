package com.example.demo.item;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Item {
    @Id
    private Long id;
    private String type;
    private String brand;
    private String model;
    private LocalDate checkInDate;

    private int status;

    public Item() {
    }

    public Item(Long id, String type, String brand, String model, LocalDate checkInDate) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.checkInDate = checkInDate;
        this.status = 0;
    }

    public Item(Long id, String type, String brand, String model, LocalDate checkInDate, int status) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.model = model;
        this.checkInDate = checkInDate;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
