/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Wassym
 */
public class Reclamation {
    private int id;
    private String Text;
    private int CreatedBy;
    private int Place_id;
    private String Location;

    public Reclamation(int id, String Text, int CreatedBy,int Place_id) {
        this.id = id;
        this.Text = Text;
        this.CreatedBy = CreatedBy;
        this.Place_id = Place_id;
    }

    public Reclamation() {
       
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public void setCreatedBy(int CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public void setPlace_id(int Place_id) {
        this.Place_id = Place_id;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return Text;
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

    @Override
    public String toString() {
        return "***************" + "\nid=" + id + ",\nText=" + Text + ",\nCreatedBy=" + CreatedBy + ",\nLocation=" + Location + "\n******************";
    }
    
    
    
}
