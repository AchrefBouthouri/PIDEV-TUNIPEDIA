/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author Wassym
 */
public class Reservation {
    private int id;
    private Date date;
    private boolean Validation;
    private String CreatedBy;
    private int Place_id;
    private String Location;

    public Reservation(int id, Date date, boolean Validation, String CreatedBy, int Place_id) {
        this.id = id;
        this.date = date;
        this.Validation = Validation;
        this.CreatedBy = CreatedBy;
        this.Place_id = Place_id;
    }

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public boolean isValidation() {
        return Validation;
    }

    public String getCreatedBy() {
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

    public void setDate(Date date) {
        this.date = date;
    }

    public void setValidation(boolean Validation) {
        this.Validation = Validation;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    @Override
    public String toString() {
        return "***************" + "\nid=" + id + ",\nDate=" + date + ",\nValidation=" + Validation + ", \nCreatedBy=" + CreatedBy + ", \nLocation=" + Location + "\n****************";
    }



    
    
}

