package com.example.demo.item;

import javax.persistence.Entity;
import java.time.LocalDate;


public class DeletedItem extends Item{

    private LocalDate deletionDate;
    private String deletionComment;

    public DeletedItem() {
    }

    public DeletedItem(String type, String brand, String model, LocalDate checkInDate,
                       LocalDate deletionDate, String deletionComment) {
        super(type, brand, model, checkInDate);

    }

    public DeletedItem(Long id, String type, String brand, String model, LocalDate checkInDate,
                       LocalDate deletionDate, String deletionComment) {
        super(id, type, brand, model, checkInDate);
    }

    public LocalDate getDeletionDate() {
        return deletionDate;
    }

    public void setDeletionDate(LocalDate deletionDate) {
        this.deletionDate = deletionDate;
    }

    public String getDeletionComment() {
        return deletionComment;
    }

    public void setDeletionComment(String deletionComment) {
        this.deletionComment = deletionComment;
    }


}
