package com.example.demo.item;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DeletedItem extends Item {

    private LocalDate deletionDate;
    private String deletionComment;

    public DeletedItem() {
    }

    public DeletedItem(Item item, String deletionComment) {
        super(item.getId(), item.getType(), item.getBrand(), item.getModel(), item.getCheckInDate(), 1);
        this.deletionDate = LocalDate.now();
        this.deletionComment = deletionComment;
    }

    public LocalDate getDeletionDate() {
        return deletionDate;
    }

//    public void setDeletionDate(LocalDate deletionDate) {
//        this.deletionDate = deletionDate;
//    }

    public String getDeletionComment() {
        return deletionComment;
    }

    public void setDeletionComment(String deletionComment) {
        this.deletionComment = deletionComment;
    }

}
