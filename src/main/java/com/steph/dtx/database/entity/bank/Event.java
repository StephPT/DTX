package com.steph.dtx.database.entity.bank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event implements Serializable {

    private String title;

    private String date;

    private String notes;

    private boolean bunting;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setBunting(boolean bunting) {
        this.bunting = bunting;
    }

    public boolean isBunting() {
        return bunting;
    }
}
