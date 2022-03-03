/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.time.LocalDate;

/**
 *
 * @author Wassym
 */
public class Reservation {

    private int id;
    private LocalDate date;
    private boolean Validation;
    private int CreatedBy;
    private int Place_id;
    private String Location;
    private String FName;

    public Reservation(int id, LocalDate date, boolean Validation, int CreatedBy, int Place_id) {
        this.id = id;
        this.date = date;
        this.Validation = Validation;
        this.CreatedBy = CreatedBy;
        this.Place_id = Place_id;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public Reservation() {
    }

    public Reservation(LocalDate date, boolean Validation, int CreatedBy, int Place_id) {
        this.date = date;
        this.Validation = Validation;
        this.CreatedBy = CreatedBy;
        this.Place_id = Place_id;
    }


    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isValidation() {
        return Validation;
    }

    public int getCreatedBy() {
        return CreatedBy;
    }

    public int getPlace_id() {
        return Place_id;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public void setPlace_id(int Place_id) {
        this.Place_id = Place_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setValidation(boolean Validation) {
        this.Validation = Validation;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    @Override
    public String toString() {
        return "***************" + "\nid=" + id + ",\nDate=" + date + ",\nValidation=" + Validation + ", \nCreatedBy=" + CreatedBy + ", \nLocation=" + Location + ", \nFName=" + FName + "\n****************";
    }

}
